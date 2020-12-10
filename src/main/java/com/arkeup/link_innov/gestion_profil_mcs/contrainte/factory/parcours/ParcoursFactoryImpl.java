package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.parcours;

import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;

/**
 *
 * @author bona
 */
@Component
public class ParcoursFactoryImpl implements ParcoursFactory {

    @Override
    public ParcoursDTO getDTOInstance() {
        return new ParcoursDTO();
    }

    @Override
    public Parcours getEntityInstance(ParcoursDTO parcoursDTO) {
        if (parcoursDTO == null) {
            return null;
        }
        Parcours entity = new Parcours();
        entity.setId(parcoursDTO.getId());
        entity.setProfil(parcoursDTO.getProfil());
        entity.setOccupation(parcoursDTO.getOccupation());
        entity.setZipCode(parcoursDTO.getZipCode());
        entity.setLieu(parcoursDTO.getLieu());
        entity.setDescription(parcoursDTO.getDescription());
        entity.setPhoto(parcoursDTO.getPhoto());
        entity.setStartDate(parcoursDTO.getStartDate());
        entity.setEndDate(parcoursDTO.getEndDate());
        entity.setCreateDate(parcoursDTO.getCreateDate());
        entity.setModifDate(parcoursDTO.getModifDate());

        Corporation corporation = new Corporation();
            corporation.setId(parcoursDTO.getCompany().getId());
            corporation.setName(parcoursDTO.getCompany().getName());

        entity.setCompany(corporation);
        return entity;
    }
}
