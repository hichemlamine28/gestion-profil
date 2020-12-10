package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.referential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreasDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.thematicarea.ThematicAreaCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.thematicarea.ThematicAreaRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/referential/thematicarea")
public class ThematicAreaController {
	@Autowired
    ThematicAreaCUDSA thematicAreaCUDSA;

    @Autowired
    ThematicAreaRSA thematicAreaRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add ThematicArea", notes = "This WS is used to add ThematicArea information.")
    @PostMapping(path = "/add")
    public ThematicAreaDTO addThematicArea(
    		@ApiParam(name = "ThematicAreaDTO", value = "{\"label\":\"example\"}", required = true)
    		@RequestBody ThematicAreaDTO thematicAreaDTO) {
       return thematicAreaCUDSA.addThematicArea(thematicAreaDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update ThematicArea", notes = "This WS is used to update ThematicArea information.")
    @PutMapping(path = "/update")
    public ThematicAreaDTO updateThematicArea(
    		@ApiParam(name = "ThematicAreaDTO", value = "{\"label\":\"example\", \"id\":\"123456\"}", required = true)
    		@RequestBody ThematicAreaDTO thematicAreaDTO) {
        return thematicAreaCUDSA.updateThematicArea(thematicAreaDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all ThematicArea information", notes = "This WS is used to get all ThematicArea information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ThematicAreasDTO listThematicArea(Pageable pageable) {
       return thematicAreaRSA.listThematicAreas(pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete ThematicArea", notes = "This WS is used to delete ThematicArea information.")
    @DeleteMapping(path = "/delete/{id}")
    public ThematicAreaDTO deleteThematicArea(@PathVariable("id") String idThematicArea) {
        return thematicAreaCUDSA.deleteThematicArea(idThematicArea);
    }
}
