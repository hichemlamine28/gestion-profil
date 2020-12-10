package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProjectRepository;

/**
 * @author Stéphan R.
 *
 */
@Service
public class ProjectRSMImpl implements ProjectRSM {

	@Autowired
	private ProjectRepository projectRepository;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.project.ProjectRSM#getByOwnerId(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Project> getByOwnerId(String ownerId, Pageable pageable) {
		return this.projectRepository.findByOwnerId(ownerId, pageable);
	}

	@Override
	public Project getById(String projectId) {
		Optional<Project> project = this.projectRepository.findById(projectId);
		return (project.isPresent()) ? project.get() : null;
	}

	@Override
	public int getNumberProjectByUser(String ownerId) {
		return this.projectRepository.findByOwnerId(ownerId).size();
	}
}
