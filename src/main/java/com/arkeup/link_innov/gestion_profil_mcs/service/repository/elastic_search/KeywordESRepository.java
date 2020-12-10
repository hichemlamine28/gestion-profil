
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;

/**
 *
 * @author bona
 */

public interface KeywordESRepository extends ElasticsearchRepository<Keyword, String> {
    
}
