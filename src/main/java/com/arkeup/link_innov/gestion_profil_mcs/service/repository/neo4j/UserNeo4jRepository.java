package com.arkeup.link_innov.gestion_profil_mcs.service.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;

public interface UserNeo4jRepository extends Neo4jRepository<User,String> {

	@Query("MATCH (u:User)-[r:FRIEND]->(f:User) WHERE u.id = {0} RETURN f")
	List<User> getFriends(String uuidUser);
	
	@Query("MATCH (u:User)-[r1:FRIEND]->(friends) MATCH (u)-[r2:FRIEND*2]->(foaf) WHERE u.id = {0} AND NOT ((u)-[:FRIEND]->(foaf)) RETURN friends,foaf, r1, r2")
	List<User> getFriendsOfFriends(String uuidUser);
	
	List<User>  findByFriends(String uuidUser);
	
}


