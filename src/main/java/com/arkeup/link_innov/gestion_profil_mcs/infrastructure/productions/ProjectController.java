package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.productions;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.ProjectDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.project.ProjectCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.project.ProjectRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Stéphan R.
 *
 */
@RestController
@RequestMapping(value = "/production/project")
public class ProjectController {

	@Autowired
	private ProjectCUDSA projectCUDSA;

	@Autowired
	private ProjectRSA projectRSA;

	/**
	 * @param binder
	 */
	@InitBinder("projectDTO")
	protected void initOtherProjectDTOBinder(WebDataBinder binder) {
		binder.setValidator(new ProjectDTOValidator());
	}

	/**
	 * <h1>Corresponding ticket:</h1>
	 *
	 * <ul>
	 * <li><strike>http://jira.arkeup.com/browse/LKV-75</li>
	 * </ul>
	 *
	 * @param projectDTO
	 * @param errors
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Create project Production", notes = "This WS is used to add new project Production.")
	@PostMapping(path = "/create")
	public ProjectDTO create(
			@ApiParam(name = "ProjectDTO", value = "{\"title\":\"lorem ipsum dolores si amet\", \"date\":\"2018-02-05\", \"mediaDTO\":{\"id\":\"mediaUuid\"}}", required = true) @Valid @RequestBody ProjectDTO projectDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}

		return this.projectCUDSA.create(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(),
				projectDTO);
	}

	/**
	 * <h1>Corresponding ticket:</h1>
	 *
	 * <ul>
	 * <li><strike>http://jira.arkeup.com/browse/LKV-75</li>
	 * </ul>
	 *
	 * @param projectDTO
	 * @param errors
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update project Production", notes = "This WS is used to update project Production.")
	@PutMapping(path = "/update")
	public ProjectDTO update(
			@ApiParam(name = "ProjectDTO", value = "{\"id\":\"b46ec505-7f2f-4966-8d01-0d0063bc0fd0\", \"title\":\"lorem ipsum dolores si amet\", \"date\":\"2018-02-05\", \"mediaDTO\":{\"id\":\"mediaUuid\"}", required = true) @Valid @RequestBody ProjectDTO projectDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}

		return this.projectCUDSA.update(projectDTO);
	}

	/**
	 * <h1>Corresponding ticket:</h1>
	 *
	 * <ul>
	 * <li><strike>http://jira.arkeup.com/browse/LKV-76</li>
	 * </ul>
	 *
	 * @param projectId
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete project", notes = "This WS is used to delete project by Id.")
	@DeleteMapping(path = "/{id}/delete")
	public ProjectDTO delete(@PathVariable("id") String projectId) {
		return this.projectCUDSA.delete(projectId);
	}

	/**
	 * @param pageable
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get project Production", notes = "This WS is used to get user's project Production.")
	@GetMapping(path = "/list")
	@ResponseBody
	public Page<ProjectDTO> getAllProjects(Pageable pageable) {
		return this.projectRSA.getByOwnerId(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(),
				pageable);
	}

	/**
	 * @author André
	 * 
	 *         <h1>Corresponding ticket:</h1>
	 *
	 *         <ul>
	 *         <li><strike>http://jira.arkeup.com/browse/LKV-1089</li>
	 *         </ul>
	 *
	 * @param ownerId
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get number project by user", notes = "This WS is used to get number project by user.")
	@GetMapping(path = "/getNumberProjectByUser/{ownerId}")
	public NumberOfProductionDTO getNumberProjectByUser(@PathVariable("ownerId") String ownerId) {
		return this.projectRSA.getNumberProjectByUser(ownerId);
	}

	/**
	 * @author André
	 * 
	 *         <h1>Corresponding ticket:</h1>
	 *
	 *         <ul>
	 *         <li><strike>http://jira.arkeup.com/browse/LKV-1089</li>
	 *         </ul>
	 *
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get number project  for current user", notes = "This WS is used to get number project for current user.")
	@GetMapping(path = "/getNumberProject")
	public NumberOfProductionDTO getNumberProject() {
		return this.projectRSA.getNumberProjectByUser(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}

	/**
	 * <h1>Corresponding ticket:</h1>
	 *
	 * <ul>
	 * <li><strike>http://jira.arkeup.com/browse/LKV-1080</li>
	 * </ul>
	 *
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get project by id", notes = "This WS is used to get project by id.")
	@GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProjectDTO getProjectById(@PathVariable("id") String projectId) {
		return projectRSA.getById(projectId);
	}
}
