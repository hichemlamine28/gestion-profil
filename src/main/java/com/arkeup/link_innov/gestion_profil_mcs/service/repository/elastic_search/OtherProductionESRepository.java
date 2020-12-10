package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;

/**
 * n'a pas besoin d'être implémenté
 */
public interface OtherProductionESRepository extends ElasticsearchRepository<OtherProduction, String> {

}
