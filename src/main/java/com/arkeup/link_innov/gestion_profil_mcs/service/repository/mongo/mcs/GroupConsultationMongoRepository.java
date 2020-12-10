package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 *
 * @author André
 */
public interface GroupConsultationMongoRepository extends MongoRepository<GroupConsultation, String> {
    List<GroupConsultation> findByGroupIdAndUserGroupId(String groupId, String userGroupId);
}
