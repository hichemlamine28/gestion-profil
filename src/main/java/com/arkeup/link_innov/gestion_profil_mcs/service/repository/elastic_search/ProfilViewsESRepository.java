package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;

/**
 *
 * @author njaka
 */
public interface ProfilViewsESRepository extends ElasticsearchRepository<ProfileViews, String> {

}
