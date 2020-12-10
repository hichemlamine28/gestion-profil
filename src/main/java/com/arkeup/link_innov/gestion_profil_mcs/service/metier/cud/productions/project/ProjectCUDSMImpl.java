package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.project;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProjectRepository;

/**
 * @author Stéphan R.
 *
 */
@Service
public class ProjectCUDSMImpl implements ProjectCUDSM {

	@Autowired
	private ProjectRepository projectRepository;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.project.ProjectCUDSM#create(java.lang.String, com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project)
	 */
	@Override
	public Project create(String ownerId, Project project) {
		project.setId(UUID.randomUUID().toString());
		project.setOwnerId(ownerId);
		project.setCreationDate(new Date());

		return this.projectRepository.save(project);
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.project.ProjectCUDSM#update(com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project)
	 */
	@Override
	public Project update(Project project) {
		Optional<Project> result = this.projectRepository.findById(project.getId());

		if (result.isPresent()) {
			project.setModificationDate(new Date());

			return this.projectRepository.save(project);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.project.ProjectCUDSM#delete(java.lang.String)
	 */
	@Override
	public Boolean delete(String projectId) {
		try {
			this.projectRepository.deleteById(projectId);
		} catch(Exception e) {
			return false;
		}

		return true;
	}
}
