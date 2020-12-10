package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.dynamic_page;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.DynamicPageValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.dynamic_page.DynamicPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page.DynamicPageCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.dynamic_page.DynamicPageRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Corresponding ticket: {@link http://jira.arkeup.com/browse/LKV-1014}
 *
 * @author Stéphan R.
 *
 */
@Api("DynamicPage")
@RestController
@RequestMapping(value = "/dynamic/page")
public class DynamicPageController {

	@Autowired
	private DynamicPageCUDSA dynamicPageCUDSA;

	@Autowired
	private DynamicPageRSA dynamicPageRSA;

	@Autowired
	private DynamicPageValidator dynamicPageValidator;

	/**
	 * @param binder
	 */
	@InitBinder("dynamicPageDTO")
	protected void initDynamicPageDTOBinder(WebDataBinder binder) {
		binder.setValidator(dynamicPageValidator);
	}

	/**
	 * @param dynamicPageDTO
	 * @param errors
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@PostMapping(path = "/create")
	@ApiOperation(value = "Add new dynamic page")
	public @Valid DynamicPageDTO create(
			@ApiParam(name = "dynamicPageDTO", value = "{\"content\":\"content\", \"urlLabel\":\"label-url\"}", required = true) @Valid @RequestBody DynamicPageDTO dynamicPageDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}

		return this.dynamicPageCUDSA.save(dynamicPageDTO);
	}

	/**
	 * @param dynamicPageDTO
	 * @param errors
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@PutMapping(path = "/modify")
	@ApiOperation(value = "Update dynamic page changes")
	public DynamicPageDTO modify(
			@ApiParam(name = "dynamicPageDTO", value = "{\"id\": \"5ca5fa72b65f467fff94ad0a\", \"content\":\"content\", \"urlLabel\":\"label-url\"}", required = true) @Valid @RequestBody DynamicPageDTO dynamicPageDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}

		return this.dynamicPageCUDSA.update(dynamicPageDTO);
	}

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@DeleteMapping(path = "/{dynamic_page_id}/delete")
	@ApiOperation(value = "Delete a specific dynamic page")
	public Boolean delete(@PathVariable("dynamic_page_id") String dynamicPageId) {
		return this.dynamicPageCUDSA.deleteById(dynamicPageId);
	}

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@GetMapping(path = "/{dynamic_page_id}")
	@ApiOperation(value = "Fetch dynamic page by id")
	public DynamicPageDTO findById(@PathVariable("dynamic_page_id") String dynamicPageId) {
		return this.dynamicPageRSA.findById(dynamicPageId);
	}

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	@GetMapping(path = "/public/{dynamic_page_id}")
	@ApiOperation(value = "Fetch dynamic page public by id")
	public DynamicPageDTO publicDynamicPagefindById(@PathVariable("dynamic_page_id") String dynamicPageId) {
		return this.dynamicPageRSA.findById(dynamicPageId);
	}

	/**
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@GetMapping(path = "/all")
	@ApiOperation(value = "Fetch all dynamic pages")
	public List<DynamicPageDTO> findAll() {
		return this.dynamicPageRSA.findAll();
	}
}
