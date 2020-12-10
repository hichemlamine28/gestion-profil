package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.UserType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Suggestion;

@Repository
public interface SuggestionMongoRepository extends MongoRepository<Suggestion, String> {
	
//	@Query("{actionDate: { $regex: ?0 } })")
//	List<UserHistory> findByactionDate(String actionDate);
//	public UserHistory findByFirstName(String firstName);
//
//	public List<UserHistory> findByAge(int age);
	
	
	
//	 @Query("{\n" + "    $and : [\n" + "        { id: { $in: ?0 } },\n" + "        { type: ?1 },\n"
//	            + "        { $or : [\n" + "            { firstname: { $regex: ?2, $options: 'i' } },\n"
//	            + "            { lastname: { $regex: ?2, $options: 'i' } }\n" + "        ]}\n" + "    ]\n" + "}")
//	    Page<Profil> findAllById(List<String> ids, UserType type, String filter, Pageable pageable);
	 

		List<Suggestion> findByfirstname(String firstName);
}