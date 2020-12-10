package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursListDTO;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author bona
 */

public interface ParcoursRSA {

    public ParcoursListDTO getParcours(String userName, Pageable pageable);
}
