package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.project;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project.ProjectCUDSA;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.project.ProjectMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.project.ProjectRSM;

import java.util.UUID;

/**
 * @author Stéphan R.
 *
 */
@Service
public class ProjectRSAImpl implements ProjectRSA {

	@Autowired
	private ProjectRSM projectRSM;

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ProjectCUDSA projectCUDSA;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.project.ProjectRSA#getByOwnerId(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<ProjectDTO> getByOwnerId(String ownerId, Pageable pageable) {
		return this.projectMapper.patentPageToPatentDtoPage(this.projectRSM.getByOwnerId(ownerId, pageable), pageable);
	}

	@Override
	public ProjectDTO getById(String projectId) {
		Project project = projectRSM.getById(projectId);
		
		if(project == null) {
			throw new ObjetNotFoundException(new ProjectDTO(), ErrorsEnum.ERR_MCS_PROFIL_0065);
		}
		boolean hasEmptyMedia = false;
		ProjectDTO projectDTO = projectMapper.toDTO(project);
		if(projectDTO != null && StringUtils.isEmpty(projectDTO.getMediaId()))
		{
			projectDTO.setMediaId(UUID.randomUUID().toString());
			hasEmptyMedia  = true;
		}

		if(hasEmptyMedia)
			projectCUDSA.update(projectDTO);

		return projectDTO;
	}

	@Override
	public NumberOfProductionDTO getNumberProjectByUser(String ownerId) {
		NumberOfProductionDTO response = new NumberOfProductionDTO();
		response.setNumberOfProduction(this.projectRSM.getNumberProjectByUser(ownerId));
		return response;
	}
}
