package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.profil_views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfViewsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilViewersDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil_views.ProfilViewsCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil_views.ProfilViewsRSA;

import io.swagger.annotations.ApiOperation;

/**
 *
 * @author njaka
 */
@RestController
@RequestMapping(value = "/profilviews")
public class ProfilViewsController {

    @Autowired
    private ProfilViewsCUDSA profilViewsCUDSA;

    @Autowired
    private ProfilViewsRSA profilViewsRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "Save profil views", notes = "This WS is used to save profil views.")
    @PostMapping(path = "/save/{userId}")
    public NumberOfViewsDTO saveProfilViews(@PathVariable("userId") String userId) {
        String connectedUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
        return profilViewsCUDSA.save(connectedUserId, userId);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "Get number of connected user profil views", notes = "This WS is used to get number of connected user profil views.")
    @GetMapping(value = {"/count"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumberOfViewsDTO getNbProfilViews() {
        String connectedUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
        return profilViewsRSA.getNbProfilViews(connectedUserId);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLECONSULTVIEWERS)
    @ApiOperation(value = "Get number of user profil views", notes = "This WS is used to get number of user profil views.")
    @GetMapping(value = {"/count/{userId}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumberOfViewsDTO getNbProfilViewsByUserId(@PathVariable("userId") String userId) {
        return profilViewsRSA.getNbProfilViews(userId);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLECONSULTVIEWERS)
    @ApiOperation(value = "Get list of user profil views", notes = "This WS is used to get list of user profil views.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProfilViewersDTO getAllProfilViews() {
        String connectedUserId = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
        return profilViewsRSA.getProfilViews(connectedUserId);
    }

}
