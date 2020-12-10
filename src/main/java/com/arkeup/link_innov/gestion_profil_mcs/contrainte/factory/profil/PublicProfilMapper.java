package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PublicProfilDTO;

/**
 *
 * @author bona
 */

@Mapper
public interface PublicProfilMapper {
	@Mappings({
		@Mapping(source = "mediaId", target = "mediaDTO.id"),
		@Mapping(source = "backgroundId", target = "backgroundDTO.id")
	})
	PublicProfilDTO profilToProfilPublicDTO(Profil profil);
}
