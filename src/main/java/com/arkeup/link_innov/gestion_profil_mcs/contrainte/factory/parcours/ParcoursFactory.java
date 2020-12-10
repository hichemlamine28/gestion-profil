package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;

/**
 *
 * @author bona
 */
public interface ParcoursFactory {

    public ParcoursDTO getDTOInstance();

    Parcours getEntityInstance(ParcoursDTO parcoursDTO);
}
