package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;

/**
 *
 * @author bona
 */
public interface GroupMongoRepository extends MongoRepository<Group, String> {

	Optional<Group> findByNameIgnoreCase(String name);

	Page<Group> findAllByOwnerId(String ownerId, Pageable pageable);

	Page<Group> findAllByNameIgnoreCaseContainingAndOwnerIdAndIdIn(String name, String ownerId, List<String> groupIds,
			Pageable pageable);

	Page<Group> findAllByNameIgnoreCaseContainingAndIdIn(String name, List<String> groupIds, Pageable pageable);
	
	List<Group> findByNameIgnoreCaseAndIdNot(String name, String id);

	List<Group> findAllByIdIn(List<String> groupIds);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContaining(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContaining(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(List<String> groupIds, String name, Pageable pageable);

	List<Group> findAll();

	Page<Group> findAllByIdIn(List<String> groupIds, Pageable pageable);
}
