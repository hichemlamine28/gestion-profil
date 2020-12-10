package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;

/**
 * @author Stéphan R.
 *
 */
public interface DynamicPageRSM {

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	DynamicPage findById(String dynamicPageId);

	/**
	 * @param dynamicPageId
	 *
	 * @return
	 */
	Boolean isExist(String dynamicPageId);

	/**
	 * @return
	 */
	List<DynamicPage> findAll();

}
