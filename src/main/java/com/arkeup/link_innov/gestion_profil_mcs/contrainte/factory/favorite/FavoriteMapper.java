package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.favorite;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteDTO;

@Mapper
public interface FavoriteMapper {

	Favorite favoriteDtoToFavorite(FavoriteDTO favoriteDTO);

	FavoriteDTO favoriteToFavoriteDTO(Favorite favorite);

	List<FavoriteDTO> listFavoriteToListFavoriteDTO(List<Favorite> favorite);

	default Page<FavoriteDTO> favoritePageToFavoriteDtoPage(Page<Favorite> favorite, Pageable pageable) {

		List<FavoriteDTO> listFavoriteDto = listFavoriteToListFavoriteDTO(favorite.getContent());
		Page<FavoriteDTO> favoriteDTOPage = new PageImpl<>(listFavoriteDto, pageable,favorite.getTotalElements());
		return favoriteDTOPage;
	}
}
