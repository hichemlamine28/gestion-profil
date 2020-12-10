package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;

/**
 * n'a pas besoin d'être implémenté
 */
public interface PatentESRepository extends ElasticsearchRepository<Patent,String> {

}
