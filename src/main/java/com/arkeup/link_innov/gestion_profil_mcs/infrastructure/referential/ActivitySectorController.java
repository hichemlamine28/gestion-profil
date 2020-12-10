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

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.activitysector.ActivitySectorCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.activitysector.ActivitySectorRSA;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/referential/activitysector")
public class ActivitySectorController {
	
	@Autowired
    ActivitySectorCUDSA activitySectorCUDSA;

    @Autowired
    ActivitySectorRSA activitySectorRSA;

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add ActivitySector", notes = "This WS is used to add ActivitySector information.")
    @PostMapping(path = "/add")
    public ActivitySectorDTO addActivitySector(
    		@ApiParam(name = "ActivitySectorDTO", value = "{\"label\":\"example\"}", required = true)
    		@RequestBody ActivitySectorDTO activitySectorDTO) {
       return activitySectorCUDSA.addActivitySector(activitySectorDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update ActivitySector", notes = "This WS is used to update ActivitySector information.")
    @PutMapping(path = "/update")
    public ActivitySectorDTO updateActivitySector(
    		@ApiParam(name = "ActivitySectorDTO", value = "{\"label\":\"example\", \"id\":\"123456\"}", required = true)
    		@RequestBody ActivitySectorDTO activitySectorDTO) {
        return activitySectorCUDSA.updateActivitySector(activitySectorDTO);
    }

    @ApiOperation(value = "get all ActivitySector information", notes = "This WS is used to get all ActivitySector information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActivitySectorsDTO listActivitySector(Pageable pageable) {
       return activitySectorRSA.listActivitySectors(pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete ActivitySector", notes = "This WS is used to delete ActivitySector information.")
    @DeleteMapping(path = "/delete/{id}")
    public ActivitySectorDTO deleteActivitySector(@PathVariable("id") String idActivitySector) {
        return activitySectorCUDSA.deleteActivitySector(idActivitySector);
    }

}
