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

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologiesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.typology.TypologyCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.typology.TypologyRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/referential/typology")
public class TypologyController {
	@Autowired
    TypologyCUDSA typologyCUDSA;

    @Autowired
    TypologyRSA typologyRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add Typology", notes = "This WS is used to add Typology information.")
    @PostMapping(path = "/add")
    public TypologyDTO addTypology(
    		@ApiParam(name = "TypologyDTO", value = "{\"label\":\"example\"}", required = true)
    		@RequestBody TypologyDTO typologyDTO) {
       return typologyCUDSA.addTypology(typologyDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update Typology", notes = "This WS is used to update Typology information.")
    @PutMapping(path = "/update")
    public TypologyDTO updateTypology(
    		@ApiParam(name = "TypologyDTO", value = "{\"label\":\"example\", \"id\":\"123456\"}", required = true)
    		@RequestBody TypologyDTO typologyDTO) {
        return typologyCUDSA.updateTypology(typologyDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all Typology information", notes = "This WS is used to get all Typology information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public TypologiesDTO listTypology(Pageable pageable) {
       return typologyRSA.listTypologies(pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete Typology", notes = "This WS is used to delete Typology information.")
    @DeleteMapping(path = "/delete/{id}")
    public TypologyDTO deleteTypology(@PathVariable("id") String idTypology) {
        return typologyCUDSA.deleteTypology(idTypology);
    }
}
