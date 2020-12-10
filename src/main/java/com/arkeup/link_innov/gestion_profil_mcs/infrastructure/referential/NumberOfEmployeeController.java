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

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeeDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.numberofemployee.NumberOfEmployeeCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.numberofemployee.NumberOfEmployeeRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/referential/numberofemployee")
public class NumberOfEmployeeController {

	@Autowired
    NumberOfEmployeeCUDSA numberOfEmployeeCUDSA;

    @Autowired
    NumberOfEmployeeRSA numberOfEmployeeRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add NumberOfEmployee", notes = "This WS is used to add NumberOfEmployee information.")
    @PostMapping(path = "/add")
    public NumberOfEmployeeDTO addNumberOfEmployee(
    		@ApiParam(name = "NumberOfEmployeeDTO", value = "{\"label\":\"example\"}", required = true)
    		@RequestBody NumberOfEmployeeDTO numberOfEmployeeDTO) {
       return numberOfEmployeeCUDSA.addNumberOfEmployee(numberOfEmployeeDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update NumberOfEmployee", notes = "This WS is used to update NumberOfEmployee information.")
    @PutMapping(path = "/update")
    public NumberOfEmployeeDTO updateNumberOfEmployee(
    		@ApiParam(name = "NumberOfEmployeeDTO", value = "{\"label\":\"example\", \"id\":\"123456\"}", required = true)
    		@RequestBody NumberOfEmployeeDTO numberOfEmployeeDTO) {
        return numberOfEmployeeCUDSA.updateNumberOfEmployee(numberOfEmployeeDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all NumberOfEmployee information", notes = "This WS is used to get all NumberOfEmployee information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumberOfEmployeesDTO listNumberOfEmployee(Pageable pageable) {
       return numberOfEmployeeRSA.listNumberOfEmployees(pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete NumberOfEmployee", notes = "This WS is used to delete NumberOfEmployee information.")
    @DeleteMapping(path = "/delete/{id}")
    public NumberOfEmployeeDTO deleteNumberOfEmployee(@PathVariable("id") String idNumberOfEmployee) {
        return numberOfEmployeeCUDSA.deleteNumberOfEmployee(idNumberOfEmployee);
    }
}
