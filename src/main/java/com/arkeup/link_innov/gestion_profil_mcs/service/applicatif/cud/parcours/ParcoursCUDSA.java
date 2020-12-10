package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;

/**
 *
 * @author bona
 */

public interface ParcoursCUDSA {

    public ParcoursDTO addParcours(String userName, ParcoursDTO parcoursDTO);
   
    public ParcoursDTO updateParcours(String userName, ParcoursDTO parcoursDTO);
    
    public ParcoursDTO deleteParcours(String idParcours);
    
}
