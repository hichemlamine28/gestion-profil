package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import java.util.List;

/**
 *
 * @author bona
 */
public class AdminsResultDTO extends BaseDTO {

    private AdminsDTO adminsDTO;
    private List<AdminsDTO> adminsDTOs;

    public AdminsDTO getAdminsDTO() {
        return adminsDTO;
    }

    public void setAdminsDTO(AdminsDTO adminsDTO) {
        this.adminsDTO = adminsDTO;
    }

    public List<AdminsDTO> getAdminsDTOs() {
        return adminsDTOs;
    }

    public void setAdminsDTOs(List<AdminsDTO> adminsDTOs) {
        this.adminsDTOs = adminsDTOs;
    }
}
