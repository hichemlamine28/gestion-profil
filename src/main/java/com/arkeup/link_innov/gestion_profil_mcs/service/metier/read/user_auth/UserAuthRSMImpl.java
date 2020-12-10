package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.user_auth;


import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.EmailValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.RoleEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class UserAuthRSMImpl implements UserAuthRSM {

	@Autowired
	private UserAuthLdapRepository userAuthLdapRepository;

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private EmailValidator emailValidator;

	public UserAuth findByUserName(String username) {
		UserAuth user = null;

		if (emailValidator.isValid(username)) {
			user = userAuthLdapRepository.findByMail(username);
			if(user != null)
				username = user.getUsername();
		} else {
			user = userAuthLdapRepository.findByUsername(username);
		}
		if(user != null)
			user.setPassword(getPassword(username));
		return user;
	}

	@Override
	public String getPassword(String username) {
		Name userDn = getLdapName(username);
		DirContextOperations ctx = ldapTemplate.lookupContext(userDn);
		return new String((byte[]) ctx.getObjectAttribute("userPassword"));
	}
	
	public void updateUserAuthRoles(String username,List<String> roles) {
		Name userDn = LdapNameBuilder.newInstance().add("ou", "people").add("uid",username).build();

		if (roles.contains(RoleEnum.INACTIF.getValue())) {
			Attribute attr = new BasicAttribute("ou", RoleEnum.INACTIF.getValue());
			ModificationItem inactif  = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);
			ldapTemplate.modifyAttributes(userDn, new ModificationItem[] {inactif});
		}
       
		if (!roles.contains(RoleEnum.ACTIF.getValue())) {
			Attribute attr = new BasicAttribute("ou", RoleEnum.ACTIF.getValue());
			ModificationItem actif = new ModificationItem(DirContext.ADD_ATTRIBUTE, attr);
			ldapTemplate.modifyAttributes(userDn, new ModificationItem[] {actif});
		}
		
    }
	

	@Override
	public boolean userNameExists(String username) {
		final Name ldapName = getLdapName(username);
		Optional<UserAuth> optionalUserAuth = userAuthLdapRepository.findById(ldapName);
		return optionalUserAuth.isPresent();
	}

	private Name getLdapName(String username) {
		final String distinguishedName = String.format("uid=%s,ou=people", username);
		return LdapUtils.newLdapName(distinguishedName);
	}

	@Override
	public Page<UserAuth> findByRole(String role, Pageable pageable) {
		List<UserAuth> users = userAuthLdapRepository.findByRolesLikeIgnoreCase(role).orElse(new ArrayList<>(1));
		return getPaginatedUser(users, pageable);
	}
	
	@Override
	public List<String> getSubscriptionActions(String subscription) {
		List<String> list = ldapTemplate.search(query().base("ou=permission,ou=groups").where("objectClass").is("organizationalUnit").and("ou").is(subscription), new SingleAttributesMapper());
		List<String> actions = new ArrayList<>();
		for (String elem : list) {
			List<String> listActions = Arrays.asList(elem.split(","));
			for (int i = 0; i < listActions.size(); i++) {
				String act = formatAuthorization(listActions.get(i));
				if (act.startsWith("perm_")) {
					actions.add(act);
				}
			}
		}
		return actions;
	}
	
	public String formatAuthorization(String authorization) {
		return authorization.replace("ou:", "").trim();
	}
	
	
	private class SingleAttributesMapper implements AttributesMapper<String> {
		@Override
		public String mapFromAttributes(Attributes attrs) throws NamingException {
			Attribute ou = attrs.get("ou");
			return ou.toString();
		}
	}
	
	

	private Page<UserAuth> getPaginatedUser(List<UserAuth> users, Pageable pageable) {
		Page<UserAuth> pageResult = null;

		if (!users.isEmpty()) {
			List<String> usersMail = new ArrayList<>(users.size());
			users.forEach(user -> usersMail.add(user.getMail()));
			int start = (int) pageable.getOffset();
			int end = (start + pageable.getPageSize()) > users.size() ? users.size() : (start + pageable.getPageSize());
			pageResult = new PageImpl<>(users.subList(start, end), pageable, users.size());
		} else {
			pageResult = new PageImpl<>(users);
		}

		return pageResult;
	}
}
