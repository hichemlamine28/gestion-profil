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
import org.springframework.web.context.request.RequestContextHolder;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.PatentValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.PatentDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.patent.PatentCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.patent.PatentRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/production/patent")
public class PatentController {

	@Autowired
	private PatentCUDSA patentCUDSA;

	@Autowired
	private PatentRSA patentRSA;

	@InitBinder("patentDTO")
	protected void initPatentDTOBinder(WebDataBinder binder) {
		binder.setValidator(new PatentDTOValidator());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Create Patent", notes = "This WS is used to add new Patent.")
	@PostMapping(path = "/create")
	public PatentDTO create(
			@ApiParam(name = "PatentDTO", value = "{\"publicationNumber\":1234, \"mediaDTO\":{\"id\":\"mediaUuid\"}}", required = true) @Valid @RequestBody PatentDTO patentDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new PatentValidationException(errors);
		}

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		RequestContextHolder.getRequestAttributes();

		return patentCUDSA.createPatent(userName, patentDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update Patent", notes = "This WS is used to update Patent.")
	@PutMapping(path = "/update")
	public PatentDTO update(
			@ApiParam(name = "PatentDTO", value = "{\"id\":\"b46ec505-7f2f-4966-8d01-0d0063bc0fd0\",\"publicationNumber\":1234, \"mediaDTO\":{\"id\":\"mediaUuid\"}}", required = true) @Valid @RequestBody PatentDTO patentDTO,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new PatentValidationException(errors);
		}
		return patentCUDSA.updatePatent(patentDTO);
	}

	/**
	 * @param id
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete Patent", notes = "This WS is used to delete Patent by Id.")
	@DeleteMapping(path = "/{id}/delete")
	public PatentDTO delete(@PathVariable("id") String id) {
		return patentCUDSA.deletePatent(id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get Patents", notes = "This WS is used to get user's Patents.")
	@GetMapping(path = "/list")
	@ResponseBody
	public Page<PatentDTO> getAllPatents(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return patentRSA.getByOwnerId(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get patent by id", notes = "This WS is used to get patent by id.")
	@GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PatentDTO getPatentById(@PathVariable("id") String patentId) {
		return patentRSA.getById(patentId);
	}

}
