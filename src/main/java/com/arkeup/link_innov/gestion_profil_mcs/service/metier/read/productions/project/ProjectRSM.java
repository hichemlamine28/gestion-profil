package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;

/**
 * @author Stéphan R.
 *
 */
public interface ProjectRSM {

	/**
	 * @param ownerId
	 * @param pageable
	 *
	 * @return
	 */
	Page<Project> getByOwnerId(String ownerId, Pageable pageable);
	
	public Project getById(String projectId);

	int getNumberProjectByUser(String ownerId);

}
