package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import org.springframework.data.mongodb.repository.Query;

/**
 * n'a pas besoin d'être implémenté
 */
public interface OtherProductionMongoRepository extends MongoRepository<OtherProduction, String> {

    Page<OtherProduction> findByOwnerIdOrderByCreationDateDesc(String ownerId, Pageable pageable);

    List<OtherProduction> findByOwnerIdOrderByCreationDateDesc(String ownerId);

    List<OtherProduction> findAllByIdInOrderByCreationDateDesc(List<String> otherProductionIds);

    @Query("{\n" + "    $and : [\n"
            + "        { id: { $in: ?0 } },\n"
            + "        { title: { $regex: ?1, $options: 'i' } }\n"
            + "    ]\n" + "}")
    Page<OtherProduction> findAllById(List<String> productionIds, String filter, Pageable pageable);

    List<OtherProduction> findAll();
}
