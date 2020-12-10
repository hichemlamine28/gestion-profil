package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author Stéphan R.
 *
 */
public interface ProjectMongoRepository extends MongoRepository<Project, String> {

    /**
     * @param contibutorId
     * @param pageable
     *
     * @return
     */
    Page<Project> findByOwnerIdOrderByCreationDateDesc(String contibutorId, Pageable pageable);

    List<Project> findByOwnerIdOrderByCreationDateDesc(String contibutorId);

    List<Project> findAllByIdInOrderByCreationDateDesc(List<String> projectIds);

    @Query("{\n" + "    $and : [\n"
            + "        { id: { $in: ?0 } },\n"
            + "        { title: { $regex: ?1, $options: 'i' } }\n"
            + "    ]\n" + "}")
    Page<Project> findAllById(List<String> productionIds, String filter, Pageable pageable);

    List<Project> findAll();
}
