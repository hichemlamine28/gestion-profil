package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun.PageableFactory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPageable;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.CorporationRepository;

@Service
public class CorporationRSMImpl implements CorporationRSM {

    @Autowired
    CorporationRepository corporationRepository;

    @Autowired
    PageableFactory<Corporation> pageableFactory;

    @Override
    public Optional<Corporation> getCorporation(String id) {
        return corporationRepository.getCorporation(id);
    }

    @Override
    public Page<Corporation> listCorporation(Pageable pageable) {
        return corporationRepository.listCorporation(pageable);
    }

    @Override
    public Page<Corporation> findCorporation(String name, Pageable pageable) {
        return corporationRepository.findCorporation(name, pageable);
    }

    @Override
    public Page<Corporation> listCorporationByAdmin(String admin, Pageable pageable) {
        return corporationRepository.listCorporationByAdmin(admin, pageable);
    }

    @Override
    public Page<Corporation> getPaginatedCorporationsInformationsByIds(List<String> corporationIds, Pageable pageable) {
        return corporationRepository.getCorporationsInformationsByIds(corporationIds, pageable);
    }

    @Override
    public HelpPage<Corporation> listPageCorporation(HelpPageable helpPageable) {
        Pageable pageable = pageableFactory.getIstance(helpPageable);
        Page<Corporation> pageCorporation = corporationRepository.listCorporation(pageable);
        return pageableFactory.pageToHelpPage(pageCorporation);
    }

    @Override
    public Optional<Corporation> getCorporationByName(String name) {
        return corporationRepository.getCorporationByName(name);
    }

    @Override
    public Boolean isOtherCorporationExistByName(String id, String name) {
        return corporationRepository.isOtherCorporationExistByName(id, name);
    }

    @Override
    public Page<Corporation> getPaginatedCorporationsInformations(List<String> corporationIds, String filter, String categorie, Pageable pageable) {
        return corporationRepository.getCorporationsInformations(corporationIds, filter, categorie, pageable);
    }

    @Override
    public List<Corporation> findAll() {
        return corporationRepository.findAll();
    }

}
