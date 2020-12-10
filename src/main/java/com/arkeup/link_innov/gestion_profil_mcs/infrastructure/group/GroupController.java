package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.group;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.GroupDTOValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RepairRightsResultDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.group.GroupCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.group_consultation.GroupConsultationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group.GroupRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Group")
@RestController
@RequestMapping(value = "/group")
public class GroupController {

	@Autowired
	private GroupCUDSA groupCUDSA;

	@Autowired
	private GroupRSA groupRSA;

	@Autowired
	private GroupConsultationCUDSA groupConsultationCUDSA;

	@Autowired
	private GroupDTOValidator groupDTOValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
	@InitBinder("groupDTO")
	protected void initGroupDTOBinder(WebDataBinder binder) {
		binder.setValidator(groupDTOValidator);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/addGroup" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Add group information")
	public @Valid GroupDTO addGroup(
			@ApiParam(name = "groupDTO", value = "{\"groupName\":\"Nom de groupe\", \"isMembersAllowedToPost\":\"true\"}", required = true) @RequestBody GroupDTO groupDTO,
			Errors errors) {
		groupDTOValidator.validate(groupDTO, errors);
		
		
		// Get client error
				if (errors.hasErrors()) {
					GroupDTO errorsDTO = groupDTOValidator.validateDTO(groupDTO);
					if (errorsDTO != null && errorsDTO.getErrorCode() != null && !errorsDTO.getErrorCode().isEmpty()) {
						LOGGER.error("No save more then errors...");
						return errorsDTO;
					}
					LOGGER.error("Throw validation exeption " + errors.getErrorCount() + " errors...");
					throw new ValidationException(errors);
				}
		
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return groupCUDSA.create(userName, groupDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "update group", notes = "This WS is used to update group information.")
	@PutMapping(path = "/update")
	public GroupDTO updateGroup(
			@ApiParam(name = "GroupDTO", value = "{\"groupName\":\"Nom de groupe\", \"description\":\"description\", \"mediaDTO\":{\"id\":\"mediaUuid\"}, \"isPublic\":\"true|false\"}", required = true) @Valid @RequestBody GroupDTO groupDTO,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationException(errors);
		}
		return groupCUDSA.update(groupDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get all group information by owner", notes = "This WS is used to get all group information by owner.")
	@GetMapping(value = { "/list" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO listGroups(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return groupRSA.listGroupByOwner(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get all group information is member or is admin by current user", notes = "This WS is used to get all group information is member or is admin by current user.")
	@GetMapping(value = { "/listGroups" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO listGroupsIsMemberOrIsAdmin(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return groupRSA.listGroupsIsMemberOrIsAdmin(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get all group information is member or is admin by username", notes = "This WS is used to get all group information is member or is admin by  username.")
	@GetMapping(value = { "/listGroups/{username}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO listGroupsIsMemberOrIsAdminByUsername(@PathVariable("username") String userName,
			Pageable pageable) {
		return groupRSA.listGroupsIsMemberOrIsAdmin(userName, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "delete gorup", notes = "This WS is used to delete group.")
	@DeleteMapping(path = "/delete/{id}")
	public GroupDTO deleteGroups(@PathVariable("id") String idGroup) {
		return groupCUDSA.delete(idGroup);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get group information by id", notes = "This WS is used to get group information by id.")
	@GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupDTO getGroupById(@PathVariable("id") String groupId) {
		return groupRSA.getGroupById(groupId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get user's sorted groups filtered by name or by owner", notes = "This WS is used to get user's sorted groups filtered by name or by owner.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page(1..100).", defaultValue = "15"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
					+ "Default sort order is ascending. " + "Multiple sort criteria are supported.") })
	@GetMapping(value = { "/list/filtered" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO findAllByNameAndOwnerId(@RequestParam(value = "name") String nameFilter,
			@RequestParam(value = "mine") Boolean isMine, Pageable pageable) {
		String ownerId = null;
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (isMine) {
			ownerId = user.getUsername();
		}
		return groupRSA.findAllByNameAndOwnerId(nameFilter, ownerId, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Generate media token for group picture", notes = "This WS is used to generate media token for group picture.")
	@PostMapping(path = "/picture/token", params = { "groupId" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public MediaDTO generatePictureProfilToken(
			@ApiParam(value = "{\"groupId\":\"groupId\"}", required = true) @RequestParam(value = "groupId", defaultValue = "uuid") String groupId) {
		return groupCUDSA.generatePictureToken(groupId);
	}

	@ApiOperation(value = "Publish post in group", notes = "publish post in group web service")
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(path = "/post")
	@ResponseBody
	public PostDTO publishInGroup(
			@ApiParam(name = "PostDTO", value = "{\"text\":\"Poster dans un groupe\",\"personneMorale\":\"corportion-uuid\", \"mediaDTO\":{\"id\":\"\",\"defaultUrl\": \"https://my-image3.jpg\", \"type\":\"PICTURE|VIDEO|AUDIO|DOCUMENT\"}, \"groupId\":\"groupId\"}", required = true) @RequestBody PostDTO postDTO) {
		return this.groupCUDSA.publishInGroup(postDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update group picture", notes = "This WS is used to Update group picture")
	@PutMapping(path = "/update/picture", params = { "groupId" })
	public GroupDTO updatePicture(
			@ApiParam(value = "{\"groupId\":\"groupId\"}", required = true) @RequestParam String groupId,
			@ApiParam(name = "mediaDTO", value = "{\"id\":\"mediaId\",\"defaultUrl\":\"https://url.image\",\"type\":\"PICTURE\"}", required = true) @RequestBody MediaDTO mediaDTO) {
		return groupCUDSA.updatePicture(groupId, mediaDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "update group consultation", notes = "This WS is used to update group consultation information.")
	@PostMapping(path = "/saveGroupConsultation")
	public GroupConsultationDTO saveGroupConsultation(
			@ApiParam(name = "GroupConsultationDTO", value = "{\"groupId\":\"grou-uuid\"}", required = true) @RequestBody GroupConsultationDTO groupConsultationDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		groupConsultationDTO.setUserGroupId(userName);
		return groupConsultationCUDSA.saveGroupConsultation(groupConsultationDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "share group", notes = "This WS is used to share group.")
	@PutMapping(path = "/share/{groupId}")
	public GroupDTO shareGroup(@PathVariable("groupId") String groupId, @RequestBody PostDTO postDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return groupCUDSA.share(userName, groupId, postDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update user's background information in group", notes = "This WS is used to Update user's background information in group.")
	@PutMapping(path = "/update/background", params = { "groupId" })
	public GroupDTO updateBackground(
			@ApiParam(value = "{\"groupId\":\"groupId\"}", required = true) @RequestParam String groupId,
			@ApiParam(name = "mediaDTO", value = "{\"id\":\"mediaId\",\"defaultUrl\":\"https://url.image\",\"type\":\"PICTURE\"}", required = true) @RequestBody MediaDTO backgroundDTO) {
		return groupCUDSA.updateBackground(groupId, backgroundDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete background group", notes = "This WS is used to delete the background group.")
	@DeleteMapping(path = "/delete/background/{groupId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupDTO deleteBackgroundGroup(@PathVariable("groupId") String groupId) {
		return groupCUDSA.deleteBackground(groupId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Delete picture's group", notes = "This WS is used to delete the picture's group.")
	@DeleteMapping(path = "/delete/picture/{groupId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupDTO deletePictureGroup(@PathVariable("groupId") String groupId) {
		return groupCUDSA.deletePicture(groupId);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get sorted groups filtered by creation date ascendant or group name ascendant or group name descendant", notes = "Get sorted groups filtered by creation date ascendant or group name ascendant or group name descendant")
	@GetMapping(value = "/filter", produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO getFilteredGroups(
			@RequestParam(value = "filter", required = false, defaultValue = "all") String filter,
			@RequestParam(value = "stringQuery", required = false) String stringQuery, Pageable pageable) {
		return groupRSA.getFilteredGroups(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(),
				filter, stringQuery, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get user sorted groups filtered by creation date ascendant or group name ascendant or group name descendant", notes = "Get user sorted groups filtered by creation date ascendant or group name ascendant or group name descendant")
	@GetMapping(value = "/filter/{username}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO getFilteredGroupsByUsername(@PathVariable("username") String username,
			@RequestParam(value = "filter", required = false, defaultValue = "all") String filter,
			@RequestParam(value = "stringQuery", required = false) String stringQuery, Pageable pageable) {
		return groupRSA.getFilteredGroups(username, filter, stringQuery, pageable);
	}
        
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get all group information by admin", notes = "This WS is used to get all group information by admin.")
	@GetMapping(value = { "/admin/list" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO listGroupIsAdmin(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return groupRSA.listGroupIsAdmin(userName, pageable);
	}
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get all group information administered by user", notes = "This WS is used to get all group information administered by user.")
	@GetMapping(value = { "/admin/list/{username}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public GroupsDTO listGroupUserIsAdmin(@PathVariable("username") String username, Pageable pageable) {
		return groupRSA.listGroupIsAdmin(username, pageable);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update group to has media", notes = "This WS is used to update group to has media.")
	@PostMapping(path = "/setGroupHasMedia")
	public GroupDTO setGroupHasMedia(
			@ApiParam(name = "GroupHasMediaDTO", value = "{\"groupId\":\"uuid\", \"hasMedia\":\"true\"}", required = true) @RequestBody GroupHasMediaDTO groupHasMediaDTO) {
		return groupCUDSA.setGroupHasMedia(groupHasMediaDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEADMIN)
	@ApiOperation(value = "Repair group rights", notes = "This WS is used to repair group rights.")
	@PutMapping(path = "/repairGroupAdminRights")
	public RepairRightsResultDTO repairGroupAdminRights() {
		return groupCUDSA.repairGroupAdminRights();
	}
}
