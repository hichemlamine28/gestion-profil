package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import java.util.Optional;

public interface CorporationMongoRepository extends MongoRepository<Corporation, String> {

    @Query("{name: { $regex: ?0 } })")
    Page<Corporation> findByNameContaining(String name, Pageable pageable);

    Page<Corporation> findByAdminsOrSuperAdmin(String admin, String superadmin, Pageable pageable);

    Page<Corporation> findAllByIdIn(List<String> corporationIds, Pageable pageable);

    Optional<Corporation> findByNameIgnoreCase(String name);

    List<Corporation> findAllByNameIgnoreCaseAndIdNot(String name, String id);

    @Query("{\n" + "    $and : [\n"
            + "        { id: { $in: ?0 } },\n"
            + "        { name: { $regex: ?1, $options: 'i' }}\n"
            + "        ]\n" + "}")
    Page<Corporation> findAllById(List<String> corporationIds, String filter, Pageable pageable);

    @Query("{\n" + "    $and : [\n"
            + "        { id: { $in: ?0 } },\n"
            + "        { name: { $regex: ?1, $options: 'i' }},\n"
            + "        { 'type._id': ?2}\n"
            + "        ]\n" + "}")
    Page<Corporation> findAllById(List<String> corporationIds, String filter, String type, Pageable pageable);

    List<Corporation> findAll();
}
