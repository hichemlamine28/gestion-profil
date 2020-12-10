
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import org.springframework.data.domain.Page;

/**
 *
 * @author bona
 */

public class ParcoursListDTO extends BaseDTO {
    Page<ParcoursDTO> parcoursDTOs;

	public Page<ParcoursDTO> getListParcoursDTO() {
		return parcoursDTOs;
	}

	public void setListParcoursDTO(Page<ParcoursDTO> parcoursDTO) {
		this.parcoursDTOs = parcoursDTO;
	}
}
