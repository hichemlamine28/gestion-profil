package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.user_details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth.UserAuthRSA;

/**
 * Created by bgh on 18/10/18.
 */
@Component
public class UserDetailsConfig implements UserDetailsService {
    
    @Autowired
    private UserAuthRSA getUserAuthSA;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	
    	// Use ldap as datasource
    	UserAuthDTO userAuthDTO = getUserAuthSA.findByUserName(s);
    	
        if(userAuthDTO == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        List<String> roles = userAuthDTO.getRoles();
        for (String role : roles) {
        	authorities.add(new SimpleGrantedAuthority(role));
		}
        
        if (roles.contains(PermissionsAndStatusUtils.STATUSINACTIF)) 
       	 throw new ProviderNotFoundException(ErrorsEnum.ERR_MCS_INACTIF_USER.getErrorMessage());
       
        if (roles.contains(PermissionsAndStatusUtils.STATUSBLOCKED))  
      	 throw new ProviderNotFoundException(ErrorsEnum.ERR_MCS_BLOCKED_USER.getErrorMessage());
       
        return new CustomUserDetails(userAuthDTO.getPseudoName(), userAuthDTO.getUsername(), userAuthDTO.getPassword(), authorities);
    }
}
