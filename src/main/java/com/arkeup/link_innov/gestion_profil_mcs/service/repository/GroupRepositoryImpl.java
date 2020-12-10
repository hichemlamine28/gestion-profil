package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.GroupESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.GroupMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class GroupRepositoryImpl
		extends CommonMongoToESRepositoryImpl<Group, String, GroupMongoRepository, GroupESRepository>
		implements GroupRepository {

	@Override
	public Optional<Group> getInformationByName(String name) {
		return this.mongoRepository.findByNameIgnoreCase(name);
	}

	@Override
	public Optional<Group> getInformationById(String idGroup) {
		return this.mongoRepository.findById(idGroup);
	}

	@Override
	public Page<Group> findAllByOwnerId(String ownerId, Pageable pageable) {
		return mongoRepository.findAllByOwnerId(ownerId, pageable);
	}

	@Override
	public Page<Group> findAllByNameAndOwnerId(String name, String ownerId, List<String> groupIds, Pageable pageable) {
		if (ownerId == null) {
			return mongoRepository.findAllByNameIgnoreCaseContainingAndIdIn(name, groupIds, pageable);
		}
		return mongoRepository.findAllByNameIgnoreCaseContainingAndOwnerIdAndIdIn(name, ownerId, groupIds, pageable);
	}

	@Override
	public List<Group> isOtherGroupExistByName(String name, String id) {
		return this.mongoRepository.findByNameIgnoreCaseAndIdNot(name, id);
	}

	@Override
	public List<Group> findAllByIdIn(List<String> groupIds) {
		return mongoRepository.findAllByIdIn(groupIds);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContaining(String ownerId, String name, Pageable pageable) {
		return this.mongoRepository.findAllByOwnerIdAndNameIgnoreCaseContaining(ownerId, name, pageable);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(String ownerId, String name, Pageable pageable) {
		return this.mongoRepository.findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(ownerId, name, pageable);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(String ownerId, String name, Pageable pageable) {
		return this.mongoRepository.findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(ownerId,  name, pageable);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(String ownerId, String name, Pageable pageable) {
		return this.mongoRepository.findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(ownerId,  name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContaining(List<String> groupIds, String name, Pageable pageable) {
		return this.mongoRepository.findAllByIdInAndNameIgnoreCaseContaining(groupIds, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(List<String> groupIds, String name, Pageable pageable) {
		return this.mongoRepository.findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(groupIds, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(List<String> groupIds, String name, Pageable pageable) {
		return this.mongoRepository.findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(groupIds, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(List<String> groupIds, String name, Pageable pageable) {
		return this.mongoRepository.findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(groupIds, name, pageable);
	}

	@Override
	public List<Group> findAll(){
		return mongoRepository.findAll();
	}

	@Override
	public Page<Group> findAllByIdIn(List<String> groupIds, Pageable pageable){
		return mongoRepository.findAllByIdIn(groupIds, pageable);
	}
}
