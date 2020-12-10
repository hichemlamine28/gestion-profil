
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bona
 */

@Service
public class ParcoursCUDSMImpl implements ParcoursCUDSM{

    @Autowired 
    private ParcoursRepository parcoursRepository;
    
    @Override
    public Parcours saveParcours(Parcours parcours) {
        parcoursRepository.save(parcours);
        return parcours;
    }

    @Override
    public void deleteParcours(String idParcours) {
       parcoursRepository.deleteById(idParcours);
    }
    
}
