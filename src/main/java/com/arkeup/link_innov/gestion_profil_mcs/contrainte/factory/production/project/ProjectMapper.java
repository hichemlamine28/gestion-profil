package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.project;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;

/**
 * @author Stéphan R.
 *
 */
@Mapper
public interface ProjectMapper {

	ProjectDTO toDTO(Project project);

	Project toDO(ProjectDTO projectDTO);

	List<ProjectDTO> toDTOs(List<Project> projects);

	List<Project> toDOs(List<ProjectDTO> projectDTOs);

	default Page<ProjectDTO> patentPageToPatentDtoPage(Page<Project> projectPage, Pageable pageable) {
		List<ProjectDTO> projectDtos = toDTOs(projectPage.getContent());
		Page<ProjectDTO> projectDTOPage = new PageImpl<>(projectDtos, pageable, projectPage.getTotalElements());

		return projectDTOPage;
	}

	/*Must be declared for subclass*/
	ProductionsDTO productionsToProductionsDTO(Productions productions);

	Productions productionsDTOToProductions(ProductionsDTO productionsDTO);

}
