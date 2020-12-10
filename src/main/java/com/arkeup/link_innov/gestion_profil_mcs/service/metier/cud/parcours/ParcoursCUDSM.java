
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;

/**
 *
 * @author bona
 */

public interface ParcoursCUDSM {
    
     public Parcours saveParcours(Parcours parcours); 
     
     public void deleteParcours(String idParcours);
}
