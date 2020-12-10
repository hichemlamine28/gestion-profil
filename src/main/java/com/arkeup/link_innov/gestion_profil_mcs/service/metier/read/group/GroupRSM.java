package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;

/**
 *
 * @author bona
 */
public interface GroupRSM {

	Boolean isGroupExistByName(String name);

	Boolean isGroupExistById(String idGroup);

	Page<Group> findAllByOwnerId(String ownerId, Pageable pageable);

	Group findByIdGroup(String idGroup);

	Page<Group> findAllByNameAndOwnerId(String nameFilter, String ownerId, List<String> groupIds, Pageable pageable);

	boolean isOtherGroupExistByName(String name, String id);

	List<Group> findAllByIdIn(List<String> groupIds);

	List<Group> findAll();

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContaining(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByCreateDateAsc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameAsc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByOwnerIdAndNameIgnoreCaseContainingOrderByNameDesc(String ownerId, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContaining(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByCreateDateAsc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameAsc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> findAllByIdInAndNameIgnoreCaseContainingOrderByNameDesc(List<String> groupIds, String name, Pageable pageable);

	Page<Group> getPaginatedGroupes(List<String> groupIds, Pageable pageable);
}
