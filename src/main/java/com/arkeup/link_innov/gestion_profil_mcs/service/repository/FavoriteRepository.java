package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.FavoriteESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.FavoriteMongoRepository;
import java.util.List;

public interface FavoriteRepository extends CommonMongoToESRepository<Favorite, String, FavoriteMongoRepository, FavoriteESRepository> {

    Page<Favorite> findByOwnerIdAndType(String userId, String type, Pageable pageable);

    List<Favorite> findByOwnerIdAndType(String userId, String type);

    Optional<Favorite> findByOwnerIdAndTargetId(String ownerId, String targetId);

}
