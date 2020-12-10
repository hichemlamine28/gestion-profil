
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author bona
 */

public interface ParcoursRSM {
     public Page<Parcours> getParcours( String idProfil, Pageable pageable); 
     
     public Parcours findParcoursById( String idParcours); 
}
