package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.corporation;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.CorporationRepository;

@Service
public class CorporationCUDSMImpl implements CorporationCUDSM {

	@Autowired
	private CorporationRepository corporationRepository;

	@Override
	public Corporation create(Corporation corporation) {
		if(!StringUtils.hasText(corporation.getId())) {
			corporation.setId(UUID.randomUUID().toString());
		}
		corporation.setCreateDate(new Date());
		corporationRepository.save(corporation);
		return corporation;
	}

	@Override
	public Corporation update(Corporation corporation) {
		Optional<Corporation> optionalResult = corporationRepository.findById(corporation.getId());
		if (optionalResult.isPresent()) {
			corporation.setModifDate(new Date());
			return corporationRepository.save(corporation);
		}
		return null;
	}

	@Override
	public void deleteCorporation(String corporationId) {
		corporationRepository.deleteById(corporationId);
	}

	@Override
	public void delete(Corporation corporation) {
		corporationRepository.delete(corporation);
	}

	@Override
	public List<Corporation> updateAll(List<Corporation> corporations) {
		List<Corporation> corporationList = new ArrayList<>();
		Iterable<Corporation> corporationIterable = corporationRepository.saveAll(corporations);
		corporationIterable.forEach(corporationList::add);
		return corporationList;
	}
}
