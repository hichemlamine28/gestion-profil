
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.validator;

import com.arkeup.link_innov.gestion_profil_mcs.commun.logging.Log;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ParcoursDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bona
 */

public class ParcoursValidator implements Validator{
    
	@Log
	Logger log;
        
        /**
	 * This Validator validates just Parcours instances
	 */
	public boolean supports(Class clazz) {
		return ParcoursDTO.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
            
		ParcoursDTO parcours = (ParcoursDTO) obj;

		if (StringUtils.isEmpty(parcours.getOccupation())) {
			e.rejectValue("occupation", "parcours.occupation.null",
					"Parcours occupation cannot be null.");
		}
                
                if (parcours.getCompany() != null && StringUtils.isEmpty(parcours.getCompany().getName())) {
			e.rejectValue("company", "parcours.company.null",
					"Parcours institution cannot be null.");
		}
                
                if (parcours.getStartDate()== null || "".equals(parcours.getStartDate())) {
			e.rejectValue("date_debut", "parcours.date_debut.null",
					"Parcours date debut cannot be null.");
		}

		if (StringUtils.isNotEmpty(parcours.getDescription())
				&& parcours.getDescription().length() > 2000) {
			e.rejectValue("description", "parcours.description.exceed",
					"The description not confidential must not exceed 2000 characters.");
		}
	}
}
