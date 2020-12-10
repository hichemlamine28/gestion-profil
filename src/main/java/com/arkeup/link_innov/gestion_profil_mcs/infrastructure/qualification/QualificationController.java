package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.qualification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.qualification.QualificationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.qualification.QualificationRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by Patrick on 30/10/2018.
 */
@Api("Qualification")
@RestController
@RequestMapping(value = "/qualification")
public class QualificationController {
	
	@Autowired
	private QualificationCUDSA qualificationCUDSA;
	
	@Autowired
	private QualificationRSA qualificationRSA;
	
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = {"/addQualification"}, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Add qualification to user information")
    public QualificationDTO addQualification(
    		@ApiParam(name="QualificationDTO", 
    		value="{\"institution\": \"institutionId\", \"degree\": \"\", \"field\": \"\", \"startDate\": \"yyyy-mm-dd\"}",
    		required=true)
    		@RequestBody QualificationDTO qualificationDTO) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String userName = user.getUsername();
        return qualificationCUDSA.create(userName, qualificationDTO);
    }
	
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@PostMapping(value = {"/updateQualification"}, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Update an qualification")
    public QualificationDTO updateQualification(
    		@ApiParam(name="QualificationDTO", 
    		value="{\"id\": \"qualificationId\", \"institution\": \"institutionId\", \"degree\": \"\", \"field\": \"\", \"startDate\": \"yyyy-mm-dd\", \"endDate\": \"yyyy-mm-dd\" \"result\": \"\", \"description\": \"\"}",
    		required=true) 
    		@RequestBody QualificationDTO qualificationDTO) {

        return qualificationCUDSA.update(qualificationDTO);
    }
	
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@DeleteMapping(value = {"/deleteQualification/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "delete a qualification")
    public BaseDTO deleteQualification(@PathVariable("id") String id) {

        return qualificationCUDSA.delete(id);
    }
	
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = {"/getQualification/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "get qualification data")
    public QualificationDTO getQualification(@PathVariable("id") String id) {

        return qualificationRSA.getQualification(id);
    }
	
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = {"/listQualifications"}, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "list all qualifications of an user")
    public QualificationsDTO listQualification(Pageable pageable) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String userName = user.getUsername();
        
		return qualificationRSA.listQualification(userName, pageable);
    }
	
	@PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
	@GetMapping(value = {"/listQualifications/{username}"}, produces = {MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "list all qualifications of an user")
    public QualificationsDTO listQualification(@PathVariable("username") String userName, Pageable pageable) {
		return qualificationRSA.listQualification(userName, pageable);
    }

}
