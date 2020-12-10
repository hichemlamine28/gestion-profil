package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.UserType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;

/**
 *
 * @author bona
 */
public interface ProfilMongoRepository extends MongoRepository<Profil, String> {

    Profil findByUsername(String username);

    Optional<Profil> findById(String id);

    @Query("{\n" + "    $and : [\n" + "        { id: { $in: ?0 } },\n" + "        { type: ?1 },\n"
            + "        { $or : [\n" + "            { firstname: { $regex: ?2, $options: 'i' } },\n"
            + "            { lastname: { $regex: ?2, $options: 'i' } }\n" + "        ]}\n" + "    ]\n" + "}")
    Page<Profil> findAllById(List<String> ids, UserType type, String filter, Pageable pageable);

    @Query("{\n" + "    $and : [\n" + "        { id: { $in: ?0 } },\n" + "        { $or : [\n"
            + "            { firstname: { $regex: ?1, $options: 'i' } },\n"
            + "            { lastname: { $regex: ?1, $options: 'i' } }\n" + "        ]}\n" + "    ]\n" + "}")
    Page<Profil> findAllById(List<String> ids, String filter, Pageable pageable);

    List<Profil> findAllByUsernameIn(List<String> userIds);

    Page<Profil> findAllByUsernameIn(List<String> userIds, Pageable pageable);

    @Query("{\n" + "    $and : [\n" 
            + "        { username: { $in: ?0 } },\n"
            + "        {'category._id' : ?2} ,\n"
            + "        { $or : [\n"
            + "                     { firstname: { $regex: ?1, $options: 'i' } },\n"
            + "                     { lastname: { $regex: ?1, $options: 'i' } }\n" 
            + "        ]}\n"
            + " ]\n" + "}")
    Page<Profil> findAllByUsername(List<String> ids, String filter, String category, Pageable pageable);

    @Query("{\n" + "    $and : [\n" + "        { username: { $in: ?0 } },\n" + "        { $or : [\n"
            + "            { firstname: { $regex: ?1, $options: 'i' } },\n"
            + "            { lastname: { $regex: ?1, $options: 'i' } }\n" + "        ]}\n" + "    ]\n" + "}")
    Page<Profil> findAllByUsername(List<String> ids, String filter, Pageable pageable);

    Page<Profil> findTop20ByUsernameNotInOrderByCreationDateDesc(List<String> adminIds, Pageable pageable);
    
    Optional<Profil>findByEmail(String mail);

    List<Profil> findAll();

	List<Profil> findByfirstname(String firstName);
}
