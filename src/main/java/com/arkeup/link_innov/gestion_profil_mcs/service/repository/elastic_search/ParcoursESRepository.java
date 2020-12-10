
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Parcours;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *
 * @author bona
 */

public interface ParcoursESRepository extends ElasticsearchRepository<Parcours, String>  {
    
}
