package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.profil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SuggestionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.SuggestionService;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author bona
 */
@RestController
@RequestMapping(value = "/suggestion")
public class SuggestionController {

	@Autowired
	private SuggestionService serviceSuggestionService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SuggestionController.class);

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = { "/addSuggestion" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Add skill to user information")
	public void addSuggestion(@RequestBody SuggestionDTO suggestionDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		if (!userName.isEmpty()) {
			suggestionDTO.setUsername(userName);
		}
		serviceSuggestionService.create(suggestionDTO);
	}

}
