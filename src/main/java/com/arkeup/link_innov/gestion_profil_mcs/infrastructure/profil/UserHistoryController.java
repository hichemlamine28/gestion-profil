package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.profil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.ProfilAction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserHistoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.UserHistoryService;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.UserHistoryServiceImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil.ProfilCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil.ProfilRSM;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/userHistory")
public class UserHistoryController {

	@Autowired
	private UserHistoryServiceImpl personService;

	@Autowired
	private ProfilRSA profilRSA;

	@Autowired
	private ProfilCUDSM profilCUDSM;

	@Autowired
	private ProfilMapper profilFactory;

	@Autowired
	private ProfilRSM profilRSM;

	@Autowired
	private UserHistoryService userHistoryService;

	// First data fetching
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/getAll")
	public List<UserHistoryDTO> getAll() {
		List<UserHistory> histories = personService.getAll();

		return userHistoryToDTO(histories);
	}

	// Data by date
	// change method to Post to use body param
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping("/findByDate")
	public List<UserHistoryDTO> findByDate(
			@ApiParam(name = "RecommandationDTO", value = "{\"actionDate\":\"uid\"}", required = true) @RequestBody UserHistoryDTO userHistoryDTO) {

		List<UserHistory> histories = personService.getAllByDate(userHistoryDTO.getActionDate());

		return userHistoryToDTO(histories);
	}

	// Data by Date param date
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/findByDates/{date}")
	public List<UserHistoryDTO> findNow2(@PathVariable(value = "date") String date, UserHistoryDTO userHistoryDTO) {
		List<UserHistory> histories = personService.getAllByDate(date.replace("-", "/"));

		return userHistoryToDTO(histories);
	}

	// TODO Data now
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/findNow")
	public List<UserHistoryDTO> findNow(
			@ApiParam(name = "RecommandationDTO", value = "{\"actionDate\":\"uid\"}", required = true) @RequestBody UserHistoryDTO userHistoryDTO) {
		Date now = new Date();
		String nowFormattedDate = new SimpleDateFormat("dd/MM/yyyy").format(now);
		List<UserHistory> histories = personService.getAllByDate(nowFormattedDate);

		return userHistoryToDTO(histories);
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/isFirstConnection")
	public boolean isFirstConnection() {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		profilRSA.getProfil(userName);

		if (profilRSA.getProfil(userName).getOnBording() != null) {

			Profil entity = profilRSM.getInformation(user.getUsername());
			userHistoryService.addOrUbdateHistory(entity.getUsername(), ProfilAction.PROFILCONNECT.getValue(),
					ProfilAction.PROFILIDCONNECT.getValue());

			return profilRSA.getProfil(userName).getOnBording();
		} else
			return false;
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping("/updateOnBording")
	public void updateOnBording() {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();

		ProfilDTO profilDTO = profilRSA.getProfil(userName);
		profilDTO.setOnBording(true);

		profilCUDSM.update(profilFactory.profilDTOToProfil(profilDTO));
	}

	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@ApiOperation(value = "UserHistory", notes = "This WS is used to add or update connect UserHistory collection")
	@GetMapping(value = { "/addOrUpdate" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ProfilDTO getAuth() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Save User History
		Profil entity = profilRSM.getInformation(user.getUsername());
		userHistoryService.addOrUbdateHistory(entity.getUsername(), ProfilAction.PROFILCONNECT.getValue(),
				ProfilAction.PROFILIDCONNECT.getValue());
		return profilRSA.getProfil(user.getUsername());
	}

	private List<UserHistoryDTO> userHistoryToDTO(List<UserHistory> histories) {
		List<UserHistoryDTO> userHistoryDTOs = new ArrayList<>();
		for (UserHistory userHistory : histories) {
			UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
			userHistoryDTO.setActionDate(userHistory.getActionDate());
			userHistoryDTO.setUserHistoryActions(userHistory.getActions());
			userHistoryDTOs.add(userHistoryDTO);
		}
		return userHistoryDTOs;
	}

}