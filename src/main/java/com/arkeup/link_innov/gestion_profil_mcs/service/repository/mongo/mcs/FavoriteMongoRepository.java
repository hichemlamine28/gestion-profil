package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Favorite;
import java.util.List;

public interface FavoriteMongoRepository extends MongoRepository<Favorite,String> {

	Page<Favorite> findByOwnerIdAndTypeOrderByCreateDateDesc(String userId, String type, Pageable pageable);
        
        List<Favorite> findByOwnerIdAndTypeOrderByCreateDateDesc(String userId, String type);
        	
	Optional<Favorite> findByOwnerIdAndTargetId(String ownerId, String targetId);
	
}
