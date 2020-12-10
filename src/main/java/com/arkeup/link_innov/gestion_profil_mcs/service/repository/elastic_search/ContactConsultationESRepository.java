package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *
 * @author André
 */
public interface ContactConsultationESRepository extends ElasticsearchRepository<ContactConsultation, String>{
    
}
