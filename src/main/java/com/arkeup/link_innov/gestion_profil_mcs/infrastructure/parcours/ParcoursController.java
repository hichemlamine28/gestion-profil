package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.parcours;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ValidationException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator.ParcoursValidator;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursListDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.parcours.ParcoursCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.parcours.ParcoursRSA;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author bona
 */
@RestController
@RequestMapping(value = "/parcours")
public class ParcoursController {

    @Autowired
    ParcoursCUDSA parcoursCUDSA;

    @Autowired
    ParcoursRSA parcoursRSA;

    @InitBinder("parcoursDTO")
    protected void initPatentDTOBinder(WebDataBinder binder) {
        binder.setValidator(new ParcoursValidator());
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "add parcours", notes = "This WS is used to add parcours information.")
    @PostMapping(path = "/add")
    public ParcoursDTO addParcours(@Valid @RequestBody ParcoursDTO parcoursDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        return parcoursCUDSA.addParcours(userName, parcoursDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "update parcours", notes = "This WS is used to update parcours information.")
    @PutMapping(path = "/update")
    public ParcoursDTO updateParcours(@Valid @RequestBody ParcoursDTO parcoursDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        return parcoursCUDSA.updateParcours(userName, parcoursDTO);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all parcours information", notes = "This WS is used to get all parcours information.")
    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ParcoursListDTO listParcours(Pageable pageable) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = user.getUsername();
        return parcoursRSA.getParcours(userName, pageable);
    }
    
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "get all parcours information", notes = "This WS is used to get all parcours information.")
    @GetMapping(value = {"/list/{username}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ParcoursListDTO listParcours(@PathVariable("username") String userName, Pageable pageable) {
        return parcoursRSA.getParcours(userName, pageable);
    }

    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @ApiOperation(value = "delete parcours", notes = "This WS is used to delete parcours information.")
    @DeleteMapping(path = "/delete/{id}")
    public ParcoursDTO deleteParcours(@PathVariable("id") String idParcours) {
        return parcoursCUDSA.deleteParcours(idParcours);
    }
}
