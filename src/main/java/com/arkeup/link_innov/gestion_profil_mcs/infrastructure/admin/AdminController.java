/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.admin;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ReindexESDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.admin.AdminSA;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminSA adminSA;


//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	@GetMapping(value = { "/admins" }, params = { "page", "size" }, produces = { MediaType.APPLICATION_JSON_VALUE })
//	@ApiOperation(value = "list all admin by page")
//	public CustomPageDTO<String>  getAdminList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value =  "size", defaultValue = "10") int size) {
//		return userAuthRSA.findAllAdmin(page, size);
//	}
//


	@PutMapping(path = "/reindex_es")
	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@ApiOperation(value = "reindex all data in Elastic Search")
	public ReindexESDTO reindexES(){
		adminSA.reindexAllES();
		ReindexESDTO reindexESDTO = new ReindexESDTO();
        reindexESDTO.setStatus("Launched");
		return reindexESDTO;
	}
}
