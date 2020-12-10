package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import org.springframework.data.mongodb.repository.Query;

/**
 * n'a pas besoin d'être implémenté
 */
public interface PatentMongoRepository extends MongoRepository<Patent, String> {

    Page<Patent> findByOwnerIdOrderByCreationDateDesc(String contibutorId, Pageable pageable);

    List<Patent> findByOwnerIdOrderByCreationDateDesc(String contibutorId);

    List<Patent> findAllByIdInOrderByCreationDateDesc(List<String> patentIds);

    @Query("{\n" + "    $and : [\n"
            + "        { id: { $in: ?0 } },\n"
            + "        { title: { $regex: ?1, $options: 'i' } }\n"
            + "    ]\n" + "}")
    Page<Patent> findAllById(List<String> productionIds, String filter, Pageable pageable);

    List<Patent> findAll();

    List<Patent> findByPublicationNumber(String publicationNumber);
}
