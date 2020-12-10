package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.GroupRepository;

/**
 *
 * @author bona
 */
@Service
public class GroupRSMImpl implements GroupRSM {

	@Autowired
	GroupRepository groupRepository;

	@Override
	public Boolean isGroupExistByName(String name) {
		Optional<Group> group = groupRepository.getInformationByName(name);
		return group.isPresent();
	}

	@Override
	public Boolean isGroupExistById(String idGroup) {
		Optional<Group> group = groupRepository.getInformationById(idGroup);
		return group.isPresent();
	}

	@Override
	public Page<Group> findAllByOwnerId(String ownerId, Pageable pageable) {
		return groupRepository.findAllByOwnerId(ownerId, pageable);
	}

	@Override
	public Group findByIdGroup(String idGroup) {
		Optional<Group> group = groupRepository.getInformationById(idGroup);
		if (group.isPresent()) {
			return group.get();
		} else {
			return null;
		}
	}

	@Override
	public Page<Group> findAllByNameAndOwnerId(String nameFilter, String ownerId, List<String> groupIds,
			Pageable pageable) {
		return groupRepository.findAllByNameAndOwnerId(nameFilter, ownerId, groupIds, pageable);
	}

	@Override
	public boolean isOtherGroupExistByName(String name, String id) {
		List<Group> groups = groupRepository.isOtherGroupExistByName(name, id);
		return CollectionUtils.isNotEmpty(groups);
	}

	@Override
	public List<Group> findAllByIdIn(List<String> groupIds) {
		return groupRepository.findAllByIdIn(groupIds);
	}

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContaining(String ownerId, String name, Pageable pageable) {
		return groupRepository.findAllByOwnerIdAndNameIgnoreCaseContaining(ownerId, name, pageable);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(String ownerId, String name, Pageable pageable) {
		return groupRepository.findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(ownerId, name, pageable);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(String ownerId, String name, Pageable pageable) {
		return groupRepository.findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(ownerId, name, pageable);
	}

	@Override
	public Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(String ownerId, String name, Pageable pageable) {
		return groupRepository.findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(ownerId, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContaining(List<String> groupIds, String name, Pageable pageable) {
		return groupRepository.findAllByIdInAndNameIgnoreCaseContaining(groupIds, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(List<String> groupIds, String name, Pageable pageable) {
		return groupRepository.findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(groupIds, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(List<String> groupIds, String name, Pageable pageable) {
		return groupRepository.findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(groupIds, name, pageable);
	}

	@Override
	public Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(List<String> groupIds, String name, Pageable pageable) {
		return groupRepository.findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(groupIds, name, pageable);
	}

	@Override
	public Page<Group> getPaginatedGroupes(List<String> groupIds, Pageable pageable) {
		return groupRepository.findAllByIdIn(groupIds, pageable);
	}


}
