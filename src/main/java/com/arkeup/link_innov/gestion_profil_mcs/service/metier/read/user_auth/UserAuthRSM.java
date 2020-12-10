package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.user_auth;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

/**
 * Created by bgh on 18/10/18.
 */
public interface UserAuthRSM {

    public UserAuth findByUserName(String username);
    
    public void updateUserAuthRoles(String username,List<String> roles);
    
    /**
     * Check if userName (email) already exists or not.
     * @return
     */
    public boolean userNameExists(String username);
    
    List<String> getSubscriptionActions(String subscription);
    
    public Page<UserAuth> findByRole(String role, Pageable pageable); // Benj 20/12/2018
    
    public String getPassword(String userName);

}
