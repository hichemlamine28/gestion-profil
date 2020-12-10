package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.favorite;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;

public interface FavoriteCUDSM {
	
	public Favorite create(Favorite favorite);

	public void update(Favorite favorite);

	public void delete(String id);

}
