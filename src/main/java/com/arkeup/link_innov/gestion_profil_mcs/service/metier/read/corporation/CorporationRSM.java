package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.corporation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPageable;

public interface CorporationRSM {

    public Optional<Corporation> getCorporation(String id);

    public Page<Corporation> listCorporation(Pageable pageable);

    public Page<Corporation> findCorporation(String name, Pageable pageable);

    public Page<Corporation> listCorporationByAdmin(String admin, Pageable pageable);

    public Page<Corporation> getPaginatedCorporationsInformationsByIds(List<String> corporationIds, Pageable pageable);

    public HelpPage<Corporation> listPageCorporation(HelpPageable helpPageable);

    public Optional<Corporation> getCorporationByName(String name);

    public Boolean isOtherCorporationExistByName(String id, String name);

    Page<Corporation> getPaginatedCorporationsInformations(List<String> corporationIds, String filter, String categorie, Pageable pageable);

    List<Corporation> findAll();
}
