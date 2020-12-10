package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import java.util.List;

/**
 *
 * @author bona
 */
public class ProfilViewersDTO extends BaseDTO {

    private List<Profil> profilsViewers;

    public List<Profil> getProfilsViewers() {
        return profilsViewers;
    }

    public void setProfilsViewers(List<Profil> profilsViewers) {
        this.profilsViewers = profilsViewers;
    }

}
