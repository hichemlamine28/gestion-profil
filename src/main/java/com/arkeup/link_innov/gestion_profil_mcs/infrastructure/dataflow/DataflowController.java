/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.dataflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomListDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.CustomPageDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.recommandation.RecommandationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group.GroupRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.user_auth.UserAuthRSA;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/dataflow")
public class DataflowController {

	@Autowired
	private UserAuthRSA userAuthRSA;

	@Autowired
	private RecommandationCUDSA recommandationCUDSA;

	@Autowired
	private GroupRSA groupRSA;

	@Autowired
	private CorporationRSA corporationRSA;


	@Autowired
	private ProfilRSA profilRSA;

	@PreAuthorize(PermissionsAndStatusUtils.ROLEETL)
	@GetMapping(value = { "/admins" }, params = { "page", "size" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "list all admin by page")
	public CustomPageDTO<String>  getAdminList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value =  "size", defaultValue = "10") int size) {
		return userAuthRSA.findAllAdmin(page, size);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEETL)
	@GetMapping(value = { "/recommandationUser" }, params = { "recommandationId"}, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "user to notify about the recommandation Skill")
	public RecommandationDTO getUserRecommandation(@RequestParam(value = "recommandationId", defaultValue = "uuid") String recommandationId) {
		return recommandationCUDSA.getUserToNotifyAboutRecommandation(recommandationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEETL)
	@GetMapping(value = { "/getCorporationAdmins" }, params = { "corporationId"}, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Corporation admins to notify about an action")
	public CustomListDTO getCorporationAdmins(@RequestParam(value = "corporationId", defaultValue = "uuid") String corporationId) {
		return corporationRSA.getCorporationAdmins(corporationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEETL)
	@GetMapping(value = { "/getCorporation/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporation information to use on dataflow")
	public CorporationDTO getCorporation(@PathVariable("id") String id) {
		return corporationRSA.getPMInformation(id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEETL)
	@ApiOperation(value = "get group information by id", notes = "This WS is used to get group informations to use on dataflow.")
	@GetMapping(value = { "/getGroup/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupDTO getGroupById(@PathVariable("id") String groupId) {
		return groupRSA.getGroupById(groupId);
	}

	/**
	 * @param userName
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEETL)
	@ApiOperation(value = "get user information", notes = "This WS is used to get user information.")
	@GetMapping(value = { "/profil/{username}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProfilDTO getProfilByusername(@PathVariable("username") String userName) {
		return profilRSA.getProfilByUsername(userName);
	}
}
