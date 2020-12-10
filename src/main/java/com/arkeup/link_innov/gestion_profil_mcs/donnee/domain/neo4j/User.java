package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonProperty;

@NodeEntity
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "User")
public class User implements BaseNeo4jObject {

    @Id
    @org.springframework.data.annotation.Id
    //@GeneratedValue
    @JsonProperty("user_id")
    private String id;

    private String firstName;

    private String lastName;

    @Relationship(type = "FRIEND", direction = Relationship.OUTGOING)
    private List<User> friends = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
	public String getId() {
        return id;
    }

    @Override
	public void setId(String id) {
        this.id = id;
    }
}
