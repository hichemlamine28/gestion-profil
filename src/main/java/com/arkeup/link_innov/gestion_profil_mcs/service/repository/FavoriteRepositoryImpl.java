package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.FavoriteESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.FavoriteMongoRepository;
import java.util.List;

@Repository
public class FavoriteRepositoryImpl extends CommonMongoToESRepositoryImpl<Favorite, String, FavoriteMongoRepository, FavoriteESRepository> implements FavoriteRepository {

    @Override
    public Page<Favorite> findByOwnerIdAndType(String userId, String type, Pageable pageable) {
        return this.mongoRepository.findByOwnerIdAndTypeOrderByCreateDateDesc(userId, type, pageable);
    }

    @Override
    public Optional<Favorite> findByOwnerIdAndTargetId(String ownerId, String targetId) {
        return this.mongoRepository.findByOwnerIdAndTargetId(ownerId, targetId);
    }

    @Override
    public List<Favorite> findByOwnerIdAndType(String userId, String type) {
        return this.mongoRepository.findByOwnerIdAndTypeOrderByCreateDateDesc(userId, type);
    }

}
