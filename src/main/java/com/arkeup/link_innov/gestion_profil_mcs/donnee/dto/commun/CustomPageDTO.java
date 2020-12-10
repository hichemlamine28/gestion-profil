package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun;

import java.io.Serializable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomPageDTO<T> extends BaseDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private HelpPage<T> pageDTOs;

	public CustomPageDTO() {
		super();
	}

	public CustomPageDTO(HelpPage<T> pageDTOs) {
		super();
		this.pageDTOs = pageDTOs;
	}
	
	public HelpPage<T> getPageDTOs() {
		return pageDTOs;
	}

	public void setPageDTOs(HelpPage<T> pageDTOs) {
		this.pageDTOs = pageDTOs;
	}

	

}
