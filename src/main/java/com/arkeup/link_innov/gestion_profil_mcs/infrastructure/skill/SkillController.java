package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.skill;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.CustomErrorException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.SkillValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillRecommendationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.skill.SkillCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.skill.SkillRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by Patrick on 30/10/2018.
 */

@Api("Skill")
@RestController
@RequestMapping(value = "/skill")
public class SkillController {
	@Autowired
	private SkillCUDSA skillCUDSA;

	@Autowired
	private SkillRSA skillRSA;

	@InitBinder("skillDTO")
	protected void initPatentDTOBinder(WebDataBinder binder) {
		binder.setValidator(new SkillValidator());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/addSkill" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Add skill to user information")
	public SkillDTO addSkill(
			@ApiParam(name = "SkillDTO", value = "{\"skillName\":\"Eletronique\"}", required = true) @Valid @RequestBody SkillDTO skillDTO,
			Errors errors) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		if (errors.hasErrors()) {
			throw new CustomErrorException(errors.getFieldError().getCode(), skillDTO);
		}

		return skillCUDSA.create(userName, skillDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PutMapping(value = { "/updateSkill" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update a skill")
	public SkillDTO updateSkill(
			@ApiParam(name = "SkillDTO", value = "{\"id\":\"uid\", \"name\":\"Eletronique\"}", required = true) @Valid @RequestBody SkillDTO skillDTO,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new CustomErrorException(errors.getFieldError().getCode(), skillDTO);
		}

		return skillCUDSA.update(skillDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@DeleteMapping(value = { "/deleteSkill/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "delete a skill by id")
	public SkillDTO deleteSkill(@PathVariable("id") String id) {

		return skillCUDSA.delete(id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/getSkill/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get skill data")
	public SkillDTO getSkill(@PathVariable("id") String id) {

		return skillRSA.getSkill(id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listSkills" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "list all skills of the connected user")
	public SkillRecommendationsDTO listSkills(Pageable pageable) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return skillRSA.listSkills(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listSkills/{username}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "list all skills of the connected user")
	public SkillRecommendationsDTO listSkills(@PathVariable("username") String userName, Pageable pageable) {

		return skillRSA.listSkills(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/findSkills/{name}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "list skills by name")
	public SkillsDTO findBySkillName(@PathVariable("name") String name, Pageable pageable) {

		return skillRSA.findSkill(name, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/addRecommendation" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Recommend a skill")
	public RecommandationDTO addRecommendation(
			@ApiParam(name = "RecommandationDTO", value = "{\"skillId\":\"uid\", \"username\":\"username\"}", required = true) @RequestBody RecommandationDTO recommendationDTO) {
		// get connected User
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return skillCUDSA.addRecommendation(recommendationDTO, user.getUsername());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/deleteRecommendation" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "delete a recommendation to a skill")
	public RecommandationDTO deleteRecommendation(
			@ApiParam(name = "RecommandationDTO", value = "{\"skillId\":\"uid\", \"username\":\"username\"}", required = true) @RequestBody RecommandationDTO recommendationDTO) {
		// get connected User
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return skillCUDSA.deleteRecommendation(recommendationDTO, user.getUsername());
	}

	// TODO
	@ApiOperation(value = "get user Skill by its label", notes = "This WS is used to get users ids from Skill label")
	@GetMapping(value = { "/getInformation/{label}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<String> getUsersIdsBySkillsLabel(@PathVariable("label") String label) {
		return skillRSA.findUsersBySkillLabel(label);
	}
}
