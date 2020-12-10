package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.GroupESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.GroupMongoRepository;

/**
 *
 * @author bona
 */
public interface GroupRepository
		extends CommonMongoToESRepository<Group, String, GroupMongoRepository, GroupESRepository> {

	Optional<Group> getInformationByName(String name);

	Optional<Group> getInformationById(String idGroup);

	Page<Group> findAllByOwnerId(String ownerId, Pageable pageable);

	Page<Group> findAllByNameAndOwnerId(String nameFilter, String ownerId, List<String> groupIds, Pageable pageable);
	
	List<Group> isOtherGroupExistByName(String name, String id);

	List<Group> findAllByIdIn(List<String> groupIds);

	Page<Group> findAllByIdIn(List<String> groupIds, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContaining(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContaining(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(List<String> groupIds, String name, Pageable pageable);

	List<Group> findAll();
}
