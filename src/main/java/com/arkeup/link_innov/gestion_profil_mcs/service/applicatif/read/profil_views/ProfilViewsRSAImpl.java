package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil_views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfViewsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilViewersDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ActiveSubscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.AbonnementMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil_views.ProfilViewsRSM;

/**
 *
 * @author njaka
 */
@Service
public class ProfilViewsRSAImpl implements ProfilViewsRSA {

    @Autowired
    private ProfilViewsRSM profilViewsRSM;

    @Autowired

    AbonnementMCS abonnementMCS;

    @Override
    public NumberOfViewsDTO getNbProfilViews(String userId) {
        NumberOfViewsDTO numberOfViewsDTO = new NumberOfViewsDTO();

        ActiveSubscriptionDTO activeSubscriptionDTO = abonnementMCS.permissionAccess("perm_consult_viewers");
        Integer nbProfilViews = profilViewsRSM.getNbProfilViews(userId);

        if (activeSubscriptionDTO.isError() && nbProfilViews > 30) {
            numberOfViewsDTO.setNumberOfViews(30);
        } else {
            numberOfViewsDTO.setNumberOfViews(nbProfilViews);
        }
        return numberOfViewsDTO;
    }

    @Override
    public ProfilViewersDTO getProfilViews(String connectedUserId) {
        ActiveSubscriptionDTO activeSubscriptionDTO = abonnementMCS.permissionAccess("perm_consult_viewers");
        Boolean limited = false;
        if (activeSubscriptionDTO.isError()) {
            limited = true;
        }
        ProfilViewersDTO profilViewersDTO = profilViewsRSM.getProfilViews(connectedUserId, limited);
        return profilViewersDTO;
    }
}
