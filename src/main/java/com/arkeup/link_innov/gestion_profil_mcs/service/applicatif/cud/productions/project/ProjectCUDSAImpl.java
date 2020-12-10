package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.CustomNullPointerException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.project.ProjectMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.project.ProjectRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.project.ProjectCUDSM;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author Stéphan R.
 *
 */
@Service
public class ProjectCUDSAImpl implements ProjectCUDSA {

	@Autowired
	private ProjectCUDSM projectCUDSM;

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ReseauxSociauxMCS socialNetworkMCS;
	
	@Autowired
	private ProjectRSA projectRSA;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project.ProjectCUDSA#create(java.lang.String, com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO)
	 */
	@Override
	public ProjectDTO create(String ownerId, ProjectDTO projectDTO) {
		if(StringUtils.isEmpty(projectDTO.getMediaId()))
		   projectDTO.setMediaId(UUID.randomUUID().toString());
		Project project = this.projectMapper.toDO(projectDTO);

		if (project == null) {
			throw new CustomNullPointerException(projectDTO, ErrorsEnum.ERR_MCS_NULL_POINTER);
		}
		projectDTO = this.projectMapper.toDTO(this.projectCUDSM.create(ownerId, project));

		if(projectDTO != null) {
			// Create node
			return (this.socialNetworkMCS.relies(projectDTO.getId()) != null) ? projectDTO : this.delete(projectDTO.getId());
		}

		return projectDTO;
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project.ProjectCUDSA#update(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO)
	 */
	@Override
	public ProjectDTO update(ProjectDTO projectDTO) {
		if(StringUtils.isEmpty(projectDTO.getMediaId()))
			projectDTO.setMediaId(UUID.randomUUID().toString());
		Project project = this.projectMapper.toDO(projectDTO);
		project = this.projectCUDSM.update(project);

		if (project == null) {
			throw new ObjetNotFoundException(projectDTO, ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND);
		}

		return this.projectMapper.toDTO(project);
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project.ProjectCUDSA#delete(java.lang.String)
	 */
	@Override
	public ProjectDTO delete(String projectId) {
		ProjectDTO response = new ProjectDTO();
		response.setId(projectId);
		
		// Delete media if exists
		ProjectDTO projectDTO = projectRSA.getById(projectId);

		// Delete the node
		if(this.socialNetworkMCS.delete(projectId).getIsDeleted()) {
			this.projectCUDSM.delete(projectId);
		}

		return projectDTO;
	}
}
