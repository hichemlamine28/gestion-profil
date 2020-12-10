///**
// * 
// */
//package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.testpagehelp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;
//
//import io.swagger.annotations.ApiOperation;
//
//
//@RestController
//@RequestMapping(value = "/testhelppage")
//public class HelpPageController {
//	
//	/** this is a test class to show how we should get a paginated result **/
//	
//	@Autowired
//	private CorporationRSA corporationRSA;
//	
//	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
//	@GetMapping(value = { "/corporationPageableList" }, params = { "page", "size" }, produces = { MediaType.APPLICATION_JSON_VALUE })
//	@ApiOperation(value = "Pageable corporation list.")
//	public CustomPageDTO<CorporationDTO>  corporationPageableList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value =  "size", defaultValue = "10") int size) {
//		return corporationRSA.corporationPageableList(page, size);
//	}
//}
