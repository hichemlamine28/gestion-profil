package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.user_auth;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.user_auth.UserAuthRSM;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalInvalidDataException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.UserAlreadyExistException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.user_auth.UserAuthMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.RoleEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth.UserAuthRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.user_auth.UserAuthCUDSM;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.support.LdapUtils;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class UserAuthCUDSAImpl implements UserAuthCUDSA {

    @Autowired
    private UserAuthCUDSM userCUDSM;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserAuthRSA getUserAuthSA;

    @Autowired
    private UserAuthRSM userAuthRSM;
    
    @Value("${ldap.spring.peopleBase}")
    private String peopleDN;

    private List<String> getDefaultRoles() {
        List<String> roles = new ArrayList<>();
//        roles.add(RoleEnum.FREEMIUM.getValue());
        roles.add(RoleEnum.USER.getValue());
        roles.add(RoleEnum.INACTIF.getValue());
        return roles;
    }

    @Override
    public UserAuthDTO create(UserAuthDTO userAuthDTO) {
        UserAuth result = null;

        // check if the pseudoName and the email already exist
        if (userCUDSM.isMailExist(userAuthDTO.getMail())) {
            userAuthDTO.setError(true);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0403.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0403.getErrorCode());
            return userAuthDTO;
        }

        // if no role set default roles
        List<String> roles = userAuthDTO.getRoles();
        if (roles == null || roles.isEmpty()) {
            userAuthDTO.setRoles(getDefaultRoles());
        }

        // set random password for ldap creation, user can change it while validating
        // this account(mail link)
        userAuthDTO.setPassword(UUID.randomUUID().toString());
        // ajouter un username (qui sera different de sont email)

        if(StringUtils.isEmpty(userAuthDTO.getUsername()))
        {
            userAuthDTO.setUsername(UUID.randomUUID().toString());
            userAuthDTO.setPseudoName(UUID.randomUUID().toString());
        }
        // ajouter un pseudoName automatique



        UserAuth userAuth = userAuthMapper.userAuthDTOtoUserAuth(userAuthDTO);
        userAuth.setFullName(userAuthDTO.getFirstName() + " " + userAuthDTO.getLastName());
        userAuth.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
        try {
            result = userCUDSM.create(userAuth);
        } catch (NameAlreadyBoundException e) {
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            throw new UserAlreadyExistException(userAuthDTO, ErrorsEnum.ERR_MCS_USER_ALREADY_EXIST, e);
        }
        return userAuthMapper.userAuthToUserAuthDTO(result);

    }

    public UserAuthDTO getUserDetails(String username) {
        return null;
    }

    @Override
    public UserAuthDTO choosePassword(UserAuthDTO userAuthDTO, Boolean validateAccount) {

        //String userName = profilRepository.findUserNameByProfileId(userAuthDTO.getProfileId());
        String userName = userAuthDTO.getUsername();
        if (userName == null) {
            // set error code and message when no user found on mongoDB
            userAuthDTO.setError(true);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorCode());
            return userAuthDTO;
        }

        UserAuth userAuth = userCUDSM.findByUsername(userName);
        if (userAuth != null) {
            if (validateAccount) {
                List<String> roles = userAuth.getRoles();
                roles.remove(RoleEnum.INACTIF.getValue());
                roles.add(RoleEnum.ACTIF.getValue());
            }
            userAuthDTO.setMail(userAuth.getMail());
            userAuth.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
            userCUDSM.create(userAuth);
        } else {
            // set error code and message when no user found on LDAP
            userAuthDTO.setError(true);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorCode());
        }
        return userAuthDTO;
    }

    @Override
    public UserAuthDTO updatePassword(UserAuthDTO userAuthDTO, Boolean validateAccount) {

        //String userName = profilRepository.findUserNameByProfileId(userAuthDTO.getProfileId());
        String userName = userAuthDTO.getUsername();
        if (userName == null) {
            // set error code and message when no user found on mongoDB
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorCode());

            throw new ObjetNotFoundException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }

        if (StringUtils.isEmpty(userAuthDTO.getOldPassword())) {
            // set error code and message when no user found on mongoDB
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD.getErrorCode());
            throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD);
        }

        if (StringUtils.isBlank(userAuthDTO.getPassword()) || userAuthDTO.getPassword().length() < 8) {
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0022.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0022.getErrorCode());
            throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0022);
        }

        // Use ldap as datasource
        UserAuthDTO userDTO = getUserAuthSA.findByUserName(userName);
        if (userDTO == null) {
            // set error code and message when no user found on LDAP
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorMessage());
            userAuthDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_0007.getErrorCode());
            throw new ObjetNotFoundException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }

        if (!passwordEncoder.matches(userAuthDTO.getOldPassword(), userDTO.getPassword())) {
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorCode("ERR_MCS_PROFIL_INVALID_PASSWORD");
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD.getErrorMessage());
            throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_INVALID_PASSWORD);
        }

        if (passwordEncoder.matches(userAuthDTO.getPassword(), userDTO.getPassword())) {
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorCode("ERR_MCS_PROFIL_NEW_PASSWORD_SAME_OLD_PASSWORD");
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_NEW_PASSWORD_SAME_OLD_PASSWORD.getErrorMessage());
            throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_NEW_PASSWORD_SAME_OLD_PASSWORD);
        }

        userDTO.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
        UserAuth user = userAuthMapper.userAuthDTOtoUserAuth(userDTO);
        user.setId(LdapUtils.newLdapName("uid=" + userAuthDTO.getUsername() + "," + peopleDN));

        try {
            userCUDSM.create(user);
        } catch (NameAlreadyBoundException e) {
            userAuthDTO.setError(true);
            userAuthDTO.setPassword(null);
            userAuthDTO.setOldPassword(null);
            userAuthDTO.setErrorCode("ERR_MCS_USER_ALREADY_EXIST");
            userAuthDTO.setErrorMessage(ErrorsEnum.ERR_MCS_USER_ALREADY_EXIST.getErrorMessage());
            throw new FunctionalInvalidDataException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_USER_ALREADY_EXIST);
        }

        userAuthDTO.setError(false);
        userAuthDTO.setPassword(null);
        userAuthDTO.setOldPassword(null);
        userAuthDTO.setMessage("password updated succefuly");

        return userAuthDTO;
    }

    @Override
    public UserAuthDTO validateAccount(UserAuthDTO userAuthDTO) {

        String userName = userAuthDTO.getUsername();
        if (userName == null) {
            // set error code and message when no user found on mongoDB
            throw new FunctionalInvalidDataException(userAuthDTO, ErrorsEnum.ERR_MCS_PROFIL_0007);
        }
        
        UserAuth userAuth = userCUDSM.findByUsername(userName);
        if (userAuth != null) {
        	List<String> roles = userAuth.getRoles();
        	userAuthRSM.updateUserAuthRoles(userName, roles);
        } else {
            // set error code and message when no user found on LDAP
            throw new ObjetNotFoundException(new UserAuthDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
        }
        userAuthDTO.setMessage("Account activated");
        return userAuthDTO;
    }

    @Override
    public UserAuthDTO findUserAuthByUserName(String userName) {
        return userAuthMapper.userAuthToUserAuthDTO(userCUDSM.findByUsername(userName));
    }

    @Override
    public void initUserTestPremiumAuth() {

        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setFirstName("Test");
        userAuthDTO.setLastName("userPremium");
        userAuthDTO.setMail("userTestPremium@yopmail.com");
        userAuthDTO.setUsername("uuid-user-test-for-abonnement-premium");
        userAuthDTO.setPseudoName("userPremium");
        userAuthDTO.setPassword("userTestPremiumPassword");

        List<String> roles = new ArrayList<>();

        roles.add(RoleEnum.PREMIUM.getValue());
        roles.add(RoleEnum.USER.getValue());
        roles.add(RoleEnum.INACTIF.getValue());

        userAuthDTO.setRoles(roles);

        UserAuth userAuthOld = userCUDSM.findByMail(userAuthDTO.getMail());

        if(userAuthOld != null){
            userCUDSM.deleteByUsername(userAuthOld.getUsername());
        }

        if(findUserAuthByUserName(userAuthDTO.getUsername()) != null){
            userCUDSM.deleteByUsername(userAuthDTO.getUsername());
        }
        create(userAuthDTO);
        validateAccount(userAuthDTO);

    }


}
