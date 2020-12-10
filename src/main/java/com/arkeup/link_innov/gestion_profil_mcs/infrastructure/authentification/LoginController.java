package com.arkeup.link_innov.gestion_profil_mcs.infrastructure.authentification;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserInformationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.infrastructure.utils.PermissionsAndStatusUtils;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil.ProfilCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.information.UserInformationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Stéphan R.
 *
 */
@Api("Login")
@RestController
@RequestMapping(value = "/authentification")
public class LoginController {

    @Autowired
    private UserInformationRSA userInformationRSA;

    @Autowired
    private ProfilCUDSA profilCUDSA;

    @Autowired
    private ProfilRSA profilRSA;

    /**
     * <h1>Corresponding ticket: </h1>
     *
     * <ul>
     * <li>http://jira.arkeup.com/browse/LKV-40</li>
     * </ul>
     *
     * @return
     */
    @ApiOperation(value = "Login", notes = "this web service return only the connected user's information ")
    @PreAuthorize(PermissionsAndStatusUtils.ROLEUSER)
    @GetMapping(path = "/login")
    @ResponseBody
    public UserInformationDTO login() {
        final Authentication connectedUser = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((UserDetails) connectedUser.getPrincipal()).getUsername();

        // Get user information from mongodb
        UserInformationDTO userDTO = this.userInformationRSA.getInformation(username);

        if (!userDTO.isError()) {
            userDTO.setUsername(username);
            userDTO.setPassword(null);

            Collection<? extends GrantedAuthority> authorities = connectedUser.getAuthorities();

            // Get roles
            if (authorities != null && !authorities.isEmpty()) {
                List<String> roles = new ArrayList<>();

                for (GrantedAuthority grantedAuthority : authorities) {
                    roles.add(grantedAuthority.getAuthority());
                }

                userDTO.setRoles(roles);
            }
        }
        return userDTO;
    }

    @ApiOperation(value = "validation mail", notes = "this web service verifie if mail already exist")
    @GetMapping(path = "/validation/{mail}")
    public Profil validationMail(@PathVariable("mail") String mail) {
        return profilRSA.isExistMail(mail);
    }
}
