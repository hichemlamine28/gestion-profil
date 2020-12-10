package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class ActivitySectorsDTO extends BaseDTO {
	
	private Page<ActivitySectorDTO> activitySectorsDTO;

	public Page<ActivitySectorDTO> getActivitySectorsDTO() {
		return activitySectorsDTO;
	}

	public void setActivitySectorsDTO(Page<ActivitySectorDTO> activitySectorsDTO) {
		this.activitySectorsDTO = activitySectorsDTO;
	}
	
}
