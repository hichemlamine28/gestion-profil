package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *
 * @author André
 */
public interface ContactConsultationMongoRepository extends MongoRepository<ContactConsultation, String> {
    List<ContactConsultation> findByUserId(String userId);
}
