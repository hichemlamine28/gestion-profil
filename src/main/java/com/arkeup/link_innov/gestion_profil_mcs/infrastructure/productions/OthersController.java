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
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.OtherProductionDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.other.OtherCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.other.OtherRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/production/other")
public class OthersController {

	@Autowired
	private OtherCUDSA otherProductionCUDSA;

	@Autowired
	private OtherRSA otherProductionRSA;

	@InitBinder("otherProductionDTO")
	protected void initOtherProductionDTOBinder(WebDataBinder binder) {
		binder.setValidator(new OtherProductionDTOValidator());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Create Other Production", notes = "This WS is used to add new Other Production.")
	@PostMapping(path = "/create")
	public OtherProductionDTO create(
			@ApiParam(name = "OtherProductionDTO", value = "{\"postType\":\"other\", \"mediaDTO\":{\"id\":\"mediaUuid\"}}", required = true) @Valid @RequestBody OtherProductionDTO otherProductionDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return otherProductionCUDSA.createOtherProduction(userName, otherProductionDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update Other Production", notes = "This WS is used to update Other Production.")
	@PutMapping(path = "/update")
	public OtherProductionDTO update(
			@ApiParam(name = "OtherProductionDTO", value = "{\"id\":\"b46ec505-7f2f-4966-8d01-0d0063bc0fd0\",\"postType\":\"other\", \"mediaDTO\":{\"id\":\"mediaUuid\"}}", required = true) @Valid @RequestBody OtherProductionDTO otherProductionDTO,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		return otherProductionCUDSA.updateOtherProduction(otherProductionDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete OtherProduction", notes = "This WS is used to delete OtherProduction by Id.")
	@DeleteMapping(path = "/{id}/delete")
	public OtherProductionDTO delete(@PathVariable("id") String id) {
		return otherProductionCUDSA.deleteOtherProduction(id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get Other Production", notes = "This WS is used to get user's Other Production.")
	@GetMapping(path = "/list")
	@ResponseBody
	public Page<OtherProductionDTO> getAllOtherProduction(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return otherProductionRSA.getByOwnerId(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get other production by id", notes = "This WS is used to get other production by id.")
	@GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public OtherProductionDTO getOtherProductionById(@PathVariable("id") String otherProductionid) {
		return otherProductionRSA.getById(otherProductionid);
	}

}
