
package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Group;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *
 * @author bona
 */
public interface GroupESRepository  extends ElasticsearchRepository<Group, String>{
    
}
