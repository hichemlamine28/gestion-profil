package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.DynamicPage;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.DynamicPageMongoRepository;

/**
 * @author Stéphan R.
 *
 */
@Service
public class DynamicPageRSMImpl implements DynamicPageRSM {

	@Autowired
	private DynamicPageMongoRepository dynamicPageMongoRepository;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page.DynamicPageRSM#findById(java.lang.String)
	 */
	@Override
	public DynamicPage findById(String dynamicPageId) {
		Optional<DynamicPage> dynamicPageOptional = this.dynamicPageMongoRepository.findById(dynamicPageId);

		return (dynamicPageOptional.isPresent()) ? dynamicPageOptional.get() : null;
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page.DynamicPageRSM#isExist(java.lang.String)
	 */
	@Override
	public Boolean isExist(String dynamicPageId) {
		return (this.findById(dynamicPageId) != null);
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.dynamic_page.DynamicPageRSM#findAll()
	 */
	@Override
	public List<DynamicPage> findAll() {
		return this.dynamicPageMongoRepository.findAll();
	}
}
