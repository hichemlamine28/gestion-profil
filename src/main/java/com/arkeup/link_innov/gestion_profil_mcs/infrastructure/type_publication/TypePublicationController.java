package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.type_publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.TypePublicationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.type_publication.TypePublicationRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("TypePublication")
@RestController
@RequestMapping(value = "/type_publication")
public class TypePublicationController {

	@Autowired
	private TypePublicationRSA typePublicationRSA;

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get all publication types", notes = "This WS is used to get all publication types.")
	@GetMapping(path = "/get")
	@ResponseBody
	public List<TypePublicationDTO> findAll() {
		return typePublicationRSA.findAll();
	}

}
