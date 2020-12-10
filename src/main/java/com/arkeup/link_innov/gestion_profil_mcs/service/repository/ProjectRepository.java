package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ProjectESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProjectMongoRepository;

/**
 * @author Stéphan R.
 *
 */
public interface ProjectRepository extends CommonMongoToESRepository<Project, String, ProjectMongoRepository, ProjectESRepository> {

    /**
     * @param ownerId
     * @param pageable
     *
     * @return
     */
    Page<Project> findByOwnerId(String ownerId, Pageable pageable);

    List<Project> findByOwnerId(String ownerId);

    List<Project> findAllByIdIn(List<String> projectIds);

    List<Project> findAll();

    Page<Project> getPaginatedProjects(List<String> productionIds, String filter, Pageable pageable);
}
