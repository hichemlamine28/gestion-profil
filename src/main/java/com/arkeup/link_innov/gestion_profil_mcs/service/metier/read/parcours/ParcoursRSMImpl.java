package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ParcoursRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author bona
 */
@Service
public class ParcoursRSMImpl implements ParcoursRSM {

    @Autowired
    ParcoursRepository parcoursRepository;

    @Override
    public Page<Parcours> getParcours(String idProfil, Pageable pageable) {
        return parcoursRepository.getParcours(idProfil, pageable);
    }

    @Override
    public Parcours findParcoursById(String idParcours) {
        Optional<Parcours> parcours = parcoursRepository.findById(idParcours);
        if (parcours.isPresent()) {
            return parcours.get();
        } else {
            return null;
        }
    }

}
