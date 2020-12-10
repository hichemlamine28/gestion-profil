package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;

/**
 *
 * @author bona
 */
public interface ProfilCUDSA {

    public void initProfiles();

    public ProfilDTO update(ProfilDTO profilDTO);

    public ProfilDTO updatePhoneNumber(String phoneNumber);

    public ProfilDTO updateUserCorporation(String userName, String corporationId);

    public MediaDTO generatePictureToken(String userId);

    public ProfilDTO deletePicture(String profilId, String mediaId);

    public ProfilDTO deleteBackground(String profilId);

    public ProfilDTO updatePictureInProfil(String userId, MediaDTO mediaDTO);

    public ProfilDTO updateProfileInitialisedStatus(String userName, Boolean initialisedStatus);

    public ProfilDTO share(String userName, String userId, PostDTO postDTO);

    public ProfilDTO updateBackgroundPictureInProfil(String userId, MediaDTO mediaDTO);

    ProfilDTO setProfilHasMedia(ProfilHasMediaDTO profilHasMediaDTO);

    IsHasMediaUpdatedDTO updateAllHasMedia(String type);

	void sendNewAnnounceToBO(ProfilForBODTO profilForBODTO);

	void sendUpdateAnnounceToBO(ProfilForBODTO announceDTO);
}
