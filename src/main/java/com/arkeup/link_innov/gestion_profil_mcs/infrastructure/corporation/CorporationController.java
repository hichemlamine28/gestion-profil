package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.corporation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.CorporationDTOValidatorHandler;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AdminListDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationAdminDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RepairRightsResultDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.corporation.CorporationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.corporation.CorporationRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Corporation")
@RestController
@RequestMapping(value = "/corporation")
public class CorporationController {

	@Autowired
	private CorporationCUDSA corporationCUDSA;

	@Autowired
	private CorporationRSA corporationRSA;

	@Autowired
	private CorporationDTOValidatorHandler corporationDTOValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(CorporationController.class);

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/addCorporation" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Add corporation information")
	public CorporationDTO addCorporation(
			@ApiParam(name = "CorporationDTO", value = "{\"corporationName\":\"examplePM\",\"type\":{\"id\": \"uuid-category-academique\", \"name\": \"Académique\"},"
					+ "  \"typology\": {\"id\": \"typologyUUID\", \"label\": \"typologyLabel\"} ,"
					+ "  \"thematicArea\": {\"id\": \"thematicAreaUUID\", \"label\": \"thematicAreaLabel\"}}", required = true) @RequestBody CorporationDTO corporationDTO) {

		Errors errors = corporationDTOValidator.validate(corporationDTO);

		// Get client error
		if (errors.hasErrors()) {
			CorporationDTO errorsDTO = corporationDTOValidator.validateDTO(corporationDTO);
			if (errorsDTO != null && errorsDTO.getErrorCode() != null && !errorsDTO.getErrorCode().isEmpty()) {
				LOGGER.error("No save more then " + errors.getErrorCount() + " errors...");
				return errorsDTO;
			}
			LOGGER.error("Throw validation exeption " + errors.getErrorCount() + " errors...");
			throw new ValidationException(errors);
		}

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationCUDSA.create(userName, corporationDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PutMapping(value = { "/updateCorporation" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update corporation information")
	public CorporationDTO updateCorporation(
			@ApiParam(name = "CorporationDTO", value = "{\"id\":\"b46ec505-7f2f-4966-8d01-0d0063bc0fd0\",\"name\":\"Nom de l'entreprise\",\"type\":{\"id\": \"123456\", \"name\": \"type\"}, \"mediaDTO\":{\"id\":\"mediaUuid\", \"ownerId\":\"ownerId\"}}", required = true) @RequestBody CorporationDTO corporationDTO) {
		Errors errors = corporationDTOValidator.validate(corporationDTO);
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		return corporationCUDSA.update(corporationDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete corporation", notes = "This WS is used to delete corporation by Id.")
	@DeleteMapping(path = "/{corporation_id}")
	public CorporationDTO delete(@PathVariable("corporation_id") String corporationId) {
		return corporationCUDSA.deleteCorporation(corporationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/addAdmin" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Add admin user to corporation")
	public CorporationDTO addAdmin(
			@ApiParam(name = "AdminDTO", value = "{\"userId\":\"uuid\", \"corporationId\":\"324567\"}", required = true) @RequestBody AdminDTO adminDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationCUDSA.addAdmin(userName, adminDTO.getUserId(), adminDTO.getCorporationId());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/removeAdmin" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Remove admin user to corporation")
	public CorporationDTO removeAdmin(
			@ApiParam(name = "AdminDTO", value = "{\"userId\":\"uuid\", \"corporationId\":\"324567\"}", required = true) @RequestBody AdminDTO adminDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		return corporationCUDSA.removeAdmin(username, adminDTO.getUserId(), adminDTO.getCorporationId());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/setAdminList" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "set admin list to a corporation")
	public AdminListDTO setAdminList(
			@ApiParam(name = "AdminListDTO", value = "{\"id\":\"69\", \"admins\":[\"12\", \"56\"]}", required = true) @RequestBody AdminListDTO adminListDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationCUDSA.setAdminList(userName, adminListDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSERANDETL)
	@GetMapping(value = { "/getCorporation/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporation information")
	public CorporationDTO getCorporation(@PathVariable("id") String id) {

		return corporationRSA.getCorporation(id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listCorporation" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporations list")
	public CorporationsDTO listCorporation(Pageable pageable) {

		return corporationRSA.listCorporation(pageable);
	}

	// TODO End_point getAll corporation
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listAllCorporation" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporations list")
	public List<Corporation> listAllCorporation() {

		return corporationRSA.listAllCorporation();
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/findCorporation/{name}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporations list from name")
	public CorporationsDTO findCorporation(@PathVariable("name") String name, Pageable pageable) {

		return corporationRSA.findCorporation(name, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listCorporationByAdmin" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporations list from name")
	public CorporationsDTO listCorporationByAdmin(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationRSA.listCorporationByAdmin(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listCorporationByUser/{username}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporations list from name")
	public CorporationsDTO listCorporationByAdmin(@PathVariable("username") String userName, Pageable pageable) {
		return corporationRSA.listCorporationByAdmin(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = { "/listCorporationIdsByUser/{username}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "get corporations ids list from name")
	public List<String> listCorporationIdsByAdmin(@PathVariable("username") String userName, Pageable pageable) {
		return corporationRSA.listCorporationIdsByAdmin(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Certify user", notes = "Certify user web service")
	@PostMapping(path = "/certify/{corporation_id}/{user_id}")
	public CorporationDTO certifyUser(@PathVariable("corporation_id") String corporationId,
			@PathVariable("user_id") String userId) {
		return this.corporationCUDSA.certifyUser(corporationId, userId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete certification by user", notes = "Delete certification by user web service")
	@DeleteMapping(path = "/certify/{corporation_id}/{user_id}")
	public CorporationDTO deleteCertificationByUser(@PathVariable("corporation_id") String corporationId,
			@PathVariable("user_id") String userId) {
		return this.corporationCUDSA.deleteCertificationByUser(corporationId, userId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get certified users by corporation id", notes = "This WS is used to get certified users by corporation id.")
	@GetMapping(path = "certified/{corporation_id}")
	public List<ReseauSocialUserDTO> getCertifiedUsers(@PathVariable("corporation_id") String corporationId) {
		return corporationRSA.getCertifiedUsers(corporationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@ApiOperation(value = "Update certification quota", notes = "This WS is used to update corporation's certification quota.")
	@PutMapping(path = "/updateCertificationQuota")
	public CorporationDTO updateCertificationQuota(@RequestParam("corporationId") String corporationId,
			@RequestParam("quota") Integer quota) {
		return corporationCUDSA.updateQuota(corporationId, quota, true, false);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@ApiOperation(value = "Update premium account quota", notes = "This WS is used to update corporation's premium ccount quota.")
	@PutMapping(path = "/updatepremiumAccountQuota")
	public CorporationDTO updatepremiumAccountQuota(@RequestParam("corporationId") String corporationId,
			@RequestParam("quota") Integer quota) {
		return corporationCUDSA.updateQuota(corporationId, quota, false, true);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Generate media token for corporation picture", notes = "This WS is used to generate media token for corporation picture.")
	@PostMapping(path = "/picture/token", params = { "corporationId" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public MediaDTO generatePictureProfilToken(
			@ApiParam(value = "{\"corporationId\":\"corporationId\"}", required = true) @RequestParam(value = "corporationId", defaultValue = "uuid") String corporationId) {
		return corporationCUDSA.generatePictureToken(corporationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Verify permission to modify corporation profil", notes = "This WS is used to verify permission to modify corporation profil.")
	@GetMapping(path = "/permission/check", params = { "corporationId" }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CorporationAdminDTO verifyPermission(
			@ApiParam(value = "{\"corporationId\":\"corporationId\"}", required = true) @RequestParam(value = "corporationId", defaultValue = "uuid") String corporationId) {
		return corporationRSA.verifyPermission(corporationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update user picture information in profil", notes = "This WS is used to Update user picture information in profil.")
	@PutMapping(path = "/update/picture", params = { "corporationId" })
	public CorporationDTO updatePictureInformationInProfil(
			@ApiParam(value = "{\"corporationId\":\"corporationId\"}", required = true) @RequestParam String corporationId,
			@ApiParam(name = "mediaDTO", value = "{\"id\":\"mediaId\",\"defaultUrl\":\"https://url.image\",\"type\":\"PICTURE\"}", required = true) @RequestBody MediaDTO mediaDTO) {
		return corporationCUDSA.updatePictureInProfil(corporationId, mediaDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update user's background information in profil", notes = "This WS is used to Update user's background information in profil.")
	@PutMapping(path = "/update/background", params = { "corporationId" })
	public CorporationDTO updateBackgroundInformationInProfil(
			@ApiParam(value = "{\"corporationId\":\"corporationId\"}", required = true) @RequestParam String corporationId,
			@ApiParam(name = "mediaDTO", value = "{\"id\":\"mediaId\",\"defaultUrl\":\"https://url.image\",\"type\":\"PICTURE\"}", required = true) @RequestBody MediaDTO backgroundDTO) {
		return corporationCUDSA.updateBackgroundInProfil(corporationId, backgroundDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete corporation picture", notes = "This WS is used to delete corporation picture.")
	@DeleteMapping(path = "/delete/picture", params = { "corporationId", "mediaId" }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public MediaDTO deletePictureProfil(
			@ApiParam(value = "{\"corporationId\":\"corporationId\"}", required = true) @RequestParam(value = "corporationId", defaultValue = "uuid") String corporationId,
			@ApiParam(value = "{\"mediaId\":\"mediaId\"}", required = true) @RequestParam(value = "mediaId", defaultValue = "uuid") String mediaId) {
		return corporationCUDSA.deletePictureInProfil(corporationId, mediaId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete background corporation", notes = "This WS is used to delete the background corporation.")
	@DeleteMapping(path = "/delete/background/{corporationId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public CorporationDTO deleteBackgroundCorporation(@PathVariable("corporationId") String corporationId) {
		return corporationCUDSA.deleteBackground(corporationId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Share corporation profil", notes = "This WS is used to share corporation profil.")
	@PostMapping(path = "/share/{corporationId}")
	public CorporationDTO shareCorporation(@PathVariable("corporationId") String corporationId,
			@RequestBody PostDTO postDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationCUDSA.shareCorporation(userName, corporationId, postDTO, null);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Share corporation profil to group", notes = "This WS is used to share corporation profil to group.")
	@PostMapping(path = "/share/{corporationId}/group/{groupId}")
	public CorporationDTO shareCorporationToGroup(@PathVariable("corporationId") String corporationId,
			@RequestBody PostDTO postDTO, @PathVariable("groupId") String groupId) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationCUDSA.shareCorporation(userName, corporationId, postDTO, groupId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Share post corporation", notes = "This WS is used to share post corporation.")
	@PostMapping(path = "/share/{corporationId}/post/{postId}")
	public CorporationDTO sharePostCorporation(@PathVariable("corporationId") String corporationId,
			@RequestBody PostDTO postDTO, @PathVariable("postId") String postId) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return corporationCUDSA.sharePostCorporation(userName, corporationId, postDTO, postId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update corporation to has media", notes = "This WS is used to update corporation to has media.")
	@PostMapping(path = "/setCorporationHasMedia")
	public CorporationDTO setCorporationHasMedia(
			@ApiParam(name = "CorporationHasMediaDTO", value = "{\"corporationId\":\"ureturn corporationCUDSA.setCorporationHasMedia(corporationHasMediaDTO);uid\", \"hasMedia\":\"true\"}", required = true) @RequestBody CorporationHasMediaDTO corporationHasMediaDTO) {
		return corporationCUDSA.setCorporationHasMedia(corporationHasMediaDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@ApiOperation(value = "Repair corporation rights", notes = "This WS is used to repair corporation rights.")
	@PutMapping(path = "/repairCorporationAdminRights")
	public RepairRightsResultDTO repairCorporationAdminRights() {
		return corporationCUDSA.repairCorporationAdminRights();
	}
}
