
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;

/**
 *
 * @author bona
 */

public interface ThematicAreaESRepository extends ElasticsearchRepository<ThematicArea, String> {
    
}
