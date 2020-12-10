
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.group;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;

import java.util.List;

/**
 *
 * @author bona
 */
public interface GroupCUDSM {
    
     public Group create(Group group);
     
     public Group update(Group group);
     
     public void delete(String idGroup);

     List<Group> updateAll(List<Group> groups);
}
