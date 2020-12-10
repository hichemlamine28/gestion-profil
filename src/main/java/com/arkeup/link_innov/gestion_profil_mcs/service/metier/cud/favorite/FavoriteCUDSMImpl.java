package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.favorite;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectDeleteException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.FavoriteDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.FavoriteRepository;

@Service
public class FavoriteCUDSMImpl implements FavoriteCUDSM {
	
	@Autowired
	FavoriteRepository favoriteRepository;

	@Override
	public Favorite create(Favorite favorite) {
		
		favorite.setCreateDate(new Date());
		favorite.setId(UUID.randomUUID().toString());
		try {
			favoriteRepository.save(favorite);
		} catch (Exception e) {
			throw new ObjectSaveException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0063, e);
		}
		
		return favorite;
	}

	@Override
	public void update(Favorite favorite) {
		favorite.setModifDate(new Date());
		favoriteRepository.save(favorite);
		
	}

	@Override
	public void delete(String id) {
		try {
			favoriteRepository.deleteById(id);
		} catch (Exception e) {
			throw new ObjectDeleteException(new FavoriteDTO(), ErrorsEnum.ERR_MCS_PROFIL_0064, e);
		}
	}

}
