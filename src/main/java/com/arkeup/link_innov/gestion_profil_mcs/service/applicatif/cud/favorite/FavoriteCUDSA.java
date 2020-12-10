package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.favorite;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteDTO;

public interface FavoriteCUDSA {
	
	public FavoriteDTO create(String username, FavoriteDTO favoriteDTO);
	public FavoriteDTO update(String username, FavoriteDTO favoriteDTO);
	public FavoriteDTO delete(String username, String id);
	
}
