package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.dynamic_page;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.DynamicPageMongoRepository;

/**
 * @author Stéphan R.
 *
 */
@Service
public class DynamicPageCUDSMImpl implements DynamicPageCUDSM {

	@Autowired
	private DynamicPageMongoRepository dynamicPageMongoRepository;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.dynamic_page.DynamicPageCUDSM#save(com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage, java.lang.Boolean[])
	 */
	@Override
	public DynamicPage save(DynamicPage dynamicPage, Boolean... isUpdateActions) {
		Date getCurrentDate = new Date();

		if(isUpdateActions == null || isUpdateActions.length < 1) {
			dynamicPage.setCreationDate(getCurrentDate);
		} else {
			dynamicPage.setModificationDate(getCurrentDate);
		}

		return this.dynamicPageMongoRepository.save(dynamicPage);
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.dynamic_page.DynamicPageCUDSM#deleteById(java.lang.String)
	 */
	@Override
	public Boolean deleteById(String dynamicPageId) {
		try {
			this.dynamicPageMongoRepository.deleteById(dynamicPageId);
		} catch(Exception e) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
