/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;

/**
 * @author benja
 *
 */
public interface ProductionMediaCUDSA {
	public void updateMedia(ProductionsDTO productionsDTO);
	
	public void deleteMedia(ProductionsDTO productionsDTO);
}
