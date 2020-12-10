package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.role;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Role;

/**
 * Created by bgh on 18/10/18.
 */
public interface RoleCUDSM {

    public Role addUserToGroup(String roleName, String userName);
    
}
