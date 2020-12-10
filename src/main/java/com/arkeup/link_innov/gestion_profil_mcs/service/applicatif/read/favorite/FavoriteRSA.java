package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.favorite;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteStatusDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoritesDTO;

public interface FavoriteRSA {

    public FavoritesDTO listFavorite(String username, String type, Pageable pageable);

    public FavoriteStatusDTO checkFavorite(String username, String targetId);

    FavoritesDTO listFavoriteFiltered(String type, String filter, String categorie, Pageable pageable);

    FavoritesDTO listFavoriteFilter(String username, String type, String category, Pageable pageable);
}
