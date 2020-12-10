package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil_views;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilViewersDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProfilViewsRepository;
import java.util.ArrayList;

/**
 *
 * @author njaka
 */
@Service
public class ProfilViewsRSMImpl implements ProfilViewsRSM {

    @Autowired
    private ProfilViewsRepository profilViewsRepository;

    @Autowired
    ProfilRSM profilRSM;

    @Override
    public ProfileViews getByUserId(String userId) {
        List<ProfileViews> profileViewsList = this.profilViewsRepository.findByUserId(userId);
        if (CollectionUtils.isNotEmpty(profileViewsList)) {
            return profileViewsList.get(0);
        }
        return null;
    }

    @Override
    public Integer getNbProfilViews(String userId) {
        return profilViewsRepository.getNbProfilViews(userId);
    }

    @Override
    public ProfilViewersDTO getProfilViews(String connectedUserId, Boolean limited) {

        List<ProfileViews> profilViewers = this.profilViewsRepository.findByUserId(connectedUserId);

        if (limited && profilViewers.size() > 30) {
            List<ProfileViews> profilViews = profilViewers.subList(0, 30);
            profilViewers = new ArrayList<>();
            profilViewers = profilViews;
        }

        List<Profil> profils = new ArrayList<>();
        for (ProfileViews profileViewer : profilViewers) {
            Profil entity = profilRSM.getInformation(profileViewer.getUserId());
            if (entity != null) {
                profils.add(entity);
            }
        }

        ProfilViewersDTO profilViewersDTO = new ProfilViewersDTO();
        profilViewersDTO.setProfilsViewers(profils);
        return profilViewersDTO;
    }

}
