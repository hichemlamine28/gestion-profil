
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;

import java.util.List;

/**
 *
 * @author bona
 */

public interface ProfilCUDSM {
     public Profil update(Profil profil);
     
     /**
      * 
      * @param profilId
      */
     public void deleteById(String profilId);

     List<Profil> updateAllProfil(List<Profil> profil);
}
