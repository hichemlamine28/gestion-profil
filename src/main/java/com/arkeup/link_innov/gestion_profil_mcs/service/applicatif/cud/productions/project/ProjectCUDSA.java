package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;

/**
 * @author Stéphan R.
 *
 */
public interface ProjectCUDSA {

	/**
	 * @param ownerId
	 * @param projectDTO
	 *
	 * @return
	 */
	ProjectDTO create(String ownerId, ProjectDTO projectDTO);

	/**
	 * @param projectDTO
	 *
	 * @return
	 */
	ProjectDTO update(ProjectDTO projectDTO);

	/**
	 * @param projectId
	 *
	 * @return
	 */
	ProjectDTO delete(String projectId);

}
