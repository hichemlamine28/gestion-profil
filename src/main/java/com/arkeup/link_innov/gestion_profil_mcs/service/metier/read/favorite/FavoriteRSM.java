package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.favorite;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import java.util.List;

public interface FavoriteRSM {

    public Optional<Favorite> getFavorite(String id);

    public Page<Favorite> listFavoriteByUser(String userId, String type, Pageable pageable);

    List<Favorite> listFavoriteByUser(String userId, String type);

    public Favorite verifyIfFavoriteExists(String ownerId, String targetId);

}
