package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.corporation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.*;

public interface CorporationCUDSA {

	public CorporationDTO create(String username, CorporationDTO corporationDTO);

	public CorporationDTO update(CorporationDTO corporationDTO);

	public CorporationDTO addAdmin(String username, String userId, String corporationId);

	public CorporationDTO removeAdmin(String username, String adminIdToRemove, String corporationId);

	public CorporationAdminDTO isCorporationAdmin(String userId, String corporationId);

	public CorporationDTO certifyUser(String corporationId, String userId);

	public CorporationDTO deleteCertificationByUser(String corporationId, String userId);

	public CorporationDTO deleteCorporation(String corporationId);

	public CorporationDTO updateQuota(String corporationId, Integer quota, Boolean certification,
			Boolean premiumAccount);

	public MediaDTO generatePictureToken(String corporationId);

	public CorporationDTO updatePictureInProfil(String corporationId, MediaDTO mediaDTO);

	public CorporationDTO updateBackgroundInProfil(String corporationId, MediaDTO backgroundDTO);

	public MediaDTO deletePictureInProfil(String corporationId, String mediaId);

	public CorporationDTO deleteBackground(String corporationId);

	public MediaDTO deleteBackgroundInProfil(String corporationId, String mediaId);

	public AdminListDTO setAdminList(String userName, AdminListDTO adminListDTO);

	public CorporationDTO shareCorporation(String userName, String corporationId, PostDTO postDTO, String groupId);

	public CorporationDTO sharePostCorporation(String userName, String corporationId, PostDTO postDTO, String postId);

	void delete(CorporationDTO corporationDTO);

	CorporationDTO setCorporationHasMedia(CorporationHasMediaDTO corporationHasMediaDTO);

	boolean updateHasMediaCorporation();

	RepairRightsResultDTO repairCorporationAdminRights();
}
