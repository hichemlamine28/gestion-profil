package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoriesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.category.CategoryCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.category.CategoryRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Patrick on 30/10/2018.
 */
@Api("Category")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	CategoryRSA categoryRSA;

	@Autowired
	CategoryCUDSA categoryCUDSA;

	@GetMapping(value = { "/listCategories" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "list all categories of corporation")
	public CategoriesDTO listCategories() {

		return categoryRSA.listCategories();
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PutMapping(value = { "/init" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "initialize all categories of corporation")
	public CategoryDTO init() {
		return categoryCUDSA.initDatabase();
	}

}
