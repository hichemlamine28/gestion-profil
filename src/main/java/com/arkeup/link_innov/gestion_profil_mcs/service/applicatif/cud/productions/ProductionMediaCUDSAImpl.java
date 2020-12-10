/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.MediaDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.MediaMCS;

/**
 * @author benja
 *
 */
@Service
public class ProductionMediaCUDSAImpl implements ProductionMediaCUDSA {

	@Autowired
	private MediaMCS mediaMCS;
	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.ProductionMediaCUDSA#updateMedia(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO)
	 */
	@Override
	public void updateMedia(ProductionsDTO productionsDTO) {
		if(productionsDTO != null && productionsDTO.getMediaDTO() != null && StringUtils.isNotEmpty(productionsDTO.getMediaDTO().getId())) {
			MediaDTO mediaDTO = mediaMCS.getMedia(productionsDTO.getMediaDTO().getId());
			mediaDTO.setAttachedObjectId(productionsDTO.getId());
			mediaDTO = mediaMCS.updateMedia(mediaDTO);
			productionsDTO.setMediaDTO(mediaDTO);
		}
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.ProductionMediaCUDSA#deleteMedia(com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO)
	 */
	@Override
	public void deleteMedia(ProductionsDTO productionsDTO) {
		if(productionsDTO != null && productionsDTO.getMediaDTO() != null && StringUtils.isNotEmpty(productionsDTO.getMediaDTO().getId())) {
			mediaMCS.deletePicture(productionsDTO.getMediaDTO().getId());
		}
	}

}
