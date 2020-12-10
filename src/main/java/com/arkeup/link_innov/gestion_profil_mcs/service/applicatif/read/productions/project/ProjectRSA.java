package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.project;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfProductionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;

/**
 * @author Stéphan R.
 *
 */
public interface ProjectRSA {

	/**
	 * @param ownerId
	 * @param pageable
	 *
	 * @return
	 */
	Page<ProjectDTO> getByOwnerId(String ownerId, Pageable pageable);
	
	public ProjectDTO getById(String projectId);

	NumberOfProductionDTO getNumberProjectByUser(String ownerId);

}
