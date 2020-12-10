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

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.classification.ClassificationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.classification.ClassificationRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/referential/classification")
public class ClassificationController {
	
	@Autowired
    ClassificationCUDSA classificationCUDSA;

    @Autowired
    ClassificationRSA classificationRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add Classification", notes = "This WS is used to add Classification information.")
    @PostMapping(path = "/add")
    public ClassificationDTO addClassification(
    		@ApiParam(name = "ClassificationDTO", value = "{\"label\":\"example\"}", required = true)
    		@RequestBody ClassificationDTO classificationDTO) {
       return classificationCUDSA.addClassification(classificationDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update Classification", notes = "This WS is used to update Classification information.")
    @PutMapping(path = "/update")
    public ClassificationDTO updateClassification(
    		@ApiParam(name = "ClassificationDTO", value = "{\"label\":\"example\", \"id\":\"123456\"}", required = true)
    		@RequestBody ClassificationDTO classificationDTO) {
        return classificationCUDSA.updateClassification(classificationDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all Classification information", notes = "This WS is used to get all Classification information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClassificationsDTO listClassification(Pageable pageable) {
       return classificationRSA.listClassifications(pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete Classification", notes = "This WS is used to delete Classification information.")
    @DeleteMapping(path = "/delete/{id}")
    public ClassificationDTO deleteClassification(@PathVariable("id") String idClassification) {
        return classificationCUDSA.deleteClassification(idClassification);
    }

}
