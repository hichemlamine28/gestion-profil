package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.project;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;

/**
 * @author Stéphan R.
 *
 */
public interface ProjectCUDSM {

	/**
	 * @param ownerId
	 * @param project
	 *
	 * @return
	 */
	Project create(String ownerId, Project project);

	/**
	 * @param project
	 *
	 * @return
	 */
	Project update(Project project);

	/**
	 * @param projectId
	 *
	 * @return
	 */
	Boolean delete(String projectId);

}
