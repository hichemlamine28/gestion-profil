package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.favorite;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.FavoriteRepository;
import java.util.List;

@Service
public class FavoriteRSMImpl implements FavoriteRSM {
	
	@Autowired
	FavoriteRepository favoriteRepository;

	@Override
	public Optional<Favorite> getFavorite(String id) {
		return favoriteRepository.findById(id);
	}

	@Override
	public Page<Favorite> listFavoriteByUser(String userId, String type, Pageable pageable) {
		return favoriteRepository.findByOwnerIdAndType(userId, type, pageable);
	}
        
	@Override
	public List<Favorite> listFavoriteByUser(String userId, String type) {
		return favoriteRepository.findByOwnerIdAndType(userId, type);
	}

	@Override
	public Favorite verifyIfFavoriteExists(String ownerId, String targetId) {
		Optional<Favorite> favorite = favoriteRepository.findByOwnerIdAndTargetId(ownerId, targetId);
		return (favorite.isPresent()) ? favorite.get() : null;
	}

}
