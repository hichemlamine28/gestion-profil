package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ProjectESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProjectMongoRepository;

/**
 * @author Stéphan R.
 *
 */
@Repository
public class ProjectRepositoryImpl extends CommonMongoToESRepositoryImpl<Project, String, ProjectMongoRepository, ProjectESRepository> implements ProjectRepository {

    /* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProjectRepository#findByOwnerId(java.lang.String, org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<Project> findByOwnerId(String ownerId, Pageable pageable) {
        return this.mongoRepository.findByOwnerIdOrderByCreationDateDesc(ownerId, pageable);
    }

    @Override
    public List<Project> findByOwnerId(String ownerId) {
        return this.mongoRepository.findByOwnerIdOrderByCreationDateDesc(ownerId);
    }

    @Override
    public List<Project> findAllByIdIn(List<String> projectIds) {
        return this.mongoRepository.findAllByIdInOrderByCreationDateDesc(projectIds);
    }

    @Override
    public Page<Project> getPaginatedProjects(List<String> productionIds, String filter, Pageable pageable) {
        return this.mongoRepository.findAllById(productionIds, filter, pageable);
    }

    @Override
    public List<Project> findAll() {
        return this.mongoRepository.findAll();
    }
}
