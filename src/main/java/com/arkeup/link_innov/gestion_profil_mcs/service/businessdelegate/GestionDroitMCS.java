package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AdminDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AdminsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.AdminsResultDTO;

/**
 *
 * @author bona
 */
@FeignClient(name = "gestionDesDroit", url = "#{'${gestion-droit.mcs.url}'}/entity")
public interface GestionDroitMCS {

    @GetMapping(path = "/check/admin")
    public Boolean isAdministredAdmin(@RequestParam("administredId") String administredId, @RequestParam("administredType") String administredType);

    @GetMapping(path = "/get/admins")
    public List<AdminsDTO> getAdministredAdmins(@RequestParam("administredId") String administredId, @RequestParam("administredType") String administredType,
    		@RequestParam(value = "secure", defaultValue = "true", required = false) Boolean isSecured);

    @DeleteMapping(path = "/delete/admins")
    public AdminsResultDTO deleteAdminsFromAdministred(@RequestBody AdminsDTO adminsDTO);

    @DeleteMapping(path = "/delete")
    public AdminsResultDTO deleteAdministred(@RequestParam("administredId") String administredId, @RequestParam("administredType") String administredType);

    @PostMapping(path = "/new")
    public AdminsResultDTO newAdministred(@RequestBody AdminsDTO adminsDTO);

    @PostMapping(path = "/add/admins")
    public AdminsResultDTO addAdminsToAdministred(@RequestBody AdminsDTO adminsDTO);

    @GetMapping(path = "/check/group/admin")
    public List<String> listGroupsIsAdmin();
    
    @GetMapping(path = "/check/group/admin/{username}")
    public List<String> listGroupsUserIsAdmin(@PathVariable("username") String username);

    @PutMapping(path = "/repairAdminRights", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<String> repairCorporationAdminRights(@RequestBody List<AdminDetailsDTO> adminDetailsDTOS);
}
