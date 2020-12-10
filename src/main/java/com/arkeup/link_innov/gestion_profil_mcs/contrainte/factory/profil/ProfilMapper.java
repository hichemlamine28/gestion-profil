package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.reasearchgate.ProfilResearchGate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;

/**
 *
 * @author bona
 */

@Mapper
public interface ProfilMapper {

	ProfilDTO profilToProfilDTO(Profil profil);

	Profil profilDTOToProfil(ProfilDTO profilDTO);

	/**
	 * @param profils
	 *
	 * @return
	 *
	 * @author Stéphan R.
	 */
	List<ProfilDTO> toDTOs(List<Profil> profils);

	/**
	 * @param profilDTOs
	 *
	 * @return
	 *
	 * @author Stéphan R.
	 */
	List<Profil> toDOs(List<ProfilDTO> profilDTOs);

	List<ReseauSocialUserDTO> profilsToContactDTOs(List<Profil> profils);

	default HelpPage<ProfilDTO> helpPageCorporationToHelpPageCorporationDTOs(HelpPage<Profil> profils){

		List<ProfilDTO> profilDTOs = this.toDTOs(profils.getContent());

		return new HelpPage<>(profilDTOs, profils.getPageable(), profils.getTotalElements());

	}



	@Mappings({
			@Mapping(target = "male", expression = "java( profilResearchGate.getBooleanGender() )"),
			@Mapping(target = "downloadStatus",expression = "java( null )")
			 })
	ProfilDTO profilResearchGateToProfilDTO(ProfilResearchGate profilResearchGate);
}
