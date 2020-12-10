package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.context.request.async.DeferredResult;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PageContactsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilAdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PublicProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;

/**
 *
 * @author bona
 */
public interface ProfilRSA {

	public ProfilDTO getAuthInformation(String username);

	public ProfilDTO getProfil(String username);

	public ProfilDTO getProfilInformation(String username);

	public ProfilDTO getProfilAnonymous(String username);

	public ProfilDTO getProfilByUserName(String username);

	public PageContactsDTO getProfilByIds(List<String> ids, String type, String filter, Pageable pageable);

	ProfilDTO getProfilByPseudoName(String pseudoName);

	public List<ProfilDTO> getListProfilByIds(List<String> userIds);

	public CustomPageDTO<ProfilDTO> getPaginatedProfilByIds(List<String> userIds, Pageable pageable);

	public ProfilAdminDTO isOwner(String userId, String profilId);

	public ProfilDTO checkisProfileInitialised(String userId);

	public PublicProfilDTO getPublicProfil(String userId);

	public ProfilAdminDTO verifyPermission(String userId);

	public MediaDTO getContactPicture(String userId);

	public DeferredResult<ProfilDTO> exportProfilToCSV(String token, String userId);

	public String getExportFileUrl(String userId);

	public CustomPageDTO<ProfilDTO> getNewSubscribedUsers(Pageable pageable);

	CustomPageDTO<ProfilDTO> getPaginatedProfil(List<String> userIds, String filter, String categorie,
			Pageable pageable);

	Profil isExistMail(String mail);

	ProfilDTO getProfilByUsername(String username);

	// TODO
	public ProfilDTO getProfilById(String userId);

	public List<Profil> getListProfilByFirstName(User sugUser);

	public List<Profil> getListProfilByFirstNameConcatLastName(User author);

	List<ProfilDTO> getAllProfils(ProfilDTO profilDTO);

}
