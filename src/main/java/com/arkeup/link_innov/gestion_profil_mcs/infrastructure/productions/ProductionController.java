package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.productions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Suggestion;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PostDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionHasMediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SuggestionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.ProductionCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.ProductionRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.ProductionRSM;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/production")
public class ProductionController {

	@Autowired
	private ProductionRSA productionRSA;

	@Autowired
	private ProductionRSM productionRSM;

	@Autowired
	private ProductionCUDSA productionCUDSA;

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get all Productions", notes = "This WS is used to get user's productions.")
	@GetMapping(path = "/list")
	@ResponseBody
	public Page<ProductionsDTO> getAllProduction(Pageable pageable,
			@RequestParam(value = "order", defaultValue = "DESC", required = false) String order) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		return productionRSA.getByOwnerId(userName, pageable, order);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get all Productions", notes = "This WS is used to get user's productions.")
	@GetMapping(path = "/list/{username}")
	@ResponseBody
	public Page<ProductionsDTO> getAllProduction(@PathVariable("username") String userName, Pageable pageable,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) String order) {
		return productionRSA.getByOwnerId(userName, pageable, order);
	}

	/**
	 * @author André
	 * 
	 *         <h1>Corresponding ticket:</h1>
	 *
	 *         <ul>
	 *         <li><strike>http://jira.arkeup.com/browse/LKV-1089</li>
	 *         </ul>
	 *
	 * @param ownerId
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get number scientific document by user", notes = "This WS is used to get number scientific document by user.")
	@GetMapping(path = "/getNumberScientificDocumentByUser/{ownerId}")
	public NumberOfProductionDTO getNumberScientificDocumentByUser(@PathVariable("ownerId") String ownerId) {
		return this.productionRSA.getNumberScientificDocumentByUser(ownerId);
	}

	/**
	 * @author André
	 * 
	 *         <h1>Corresponding ticket:</h1>
	 *
	 *         <ul>
	 *         <li><strike>http://jira.arkeup.com/browse/LKV-1089</li>
	 *         </ul>
	 *
	 *
	 * @return
	 */
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Get number scientific document  for current user", notes = "This WS is used to get number scientific document  for current user.")
	@GetMapping(path = "/getNumberScientificDocument")
	public NumberOfProductionDTO getNumberScientificDocument() {
		return this.productionRSA.getNumberScientificDocumentByUser(
				((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "share production", notes = "This WS is used to share production.")
	@PutMapping(path = "/share/{productionId}")
	public ProductionsDTO shareProduction(@PathVariable("productionId") String productionId,
			@RequestBody PostDTO postDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return productionRSA.share(userName, productionId, postDTO);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "get production information", notes = "This WS is used to get production information.")
	@GetMapping(path = "/getInformation/{id}")
	public ProductionsDTO getProductionById(@PathVariable("id") String id) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return productionRSA.getProductionById(userName, id);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update production to has media", notes = "This WS is used to update production to has media.")
	@PostMapping(path = "/setProductionHasMedia")
	public ProductionsDTO setProductionHasMedia(
			@ApiParam(name = "ProductionHasMediaDTO", value = "{\"productionId\":\"uuid\", \"hasMedia\":\"true\"}", required = true) @RequestBody ProductionHasMediaDTO productionHasMediaDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return productionCUDSA.setProductionHasMedia(productionHasMediaDTO);
	}

	// TODO suggestion
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "All invite suggestions", notes = "This WS is used to get all invite suggestion")
	@GetMapping(path = "/list/inviteSuggestions")
	@ResponseBody
	public List<Profil> getInviteSuggestions() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return productionRSM.suggerInvitations(userName);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "All subscribe suggestions", notes = "This WS is used to get all subscribe suggestion")
	@GetMapping(path = "/list/subscribeSuggestions")
	@ResponseBody
	public List<String> getSubscribeSuggestions() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return productionRSM.suggerSubscription(userName);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "Update production to has media", notes = "This WS is used to update production to has media.")
	@PostMapping(path = "/updateSuggestion")
	public List<Suggestion> updateSuggestion(@RequestBody SuggestionDTO suggestionDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		suggestionDTO.setUsername(userName);
		return productionRSM.updateSuggestion(suggestionDTO);
	}
}
