package com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.def;

import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;

@Component
@ChangeLog(order = "001")
public class PatentUpdates {

	private String collectionName;

	@ChangeSet(order = "001", id = "addPublicationDateToPatent", runAlways = true, author = "Njaka")
	public void addPublicationDateToPatent(MongoTemplate mongoTemplate) {
		collectionName = mongoTemplate.getCollectionName(Patent.class).toLowerCase();
		String columnName = "publicationDate";
		Date value = new Date();
		addField(columnName, value, mongoTemplate);

	}

	@ChangeSet(order = "002", id = "addDepositorToPatent", runAlways = true, author = "Njaka")
	public void addDepositorToPatent(MongoTemplate mongoTemplate) {
		String columnName = "depositor";
		String value = "20fb0afc-f958-11e8-8eb2-f2801f1b9fd1";
		addField(columnName, value, mongoTemplate);

	}

	private void addField(String columnName, Object value, MongoTemplate mongoTemplate) {
		BasicDBObject newValues = new BasicDBObject(columnName, value);
		BasicDBObject set = new BasicDBObject("$set", newValues);

		BasicDBObject query = new BasicDBObject(columnName, new BasicDBObject("$exists", false));

		mongoTemplate.getCollection(collectionName).updateMany(query, set);
	}

	private void updateField(String columnName, Object value, BasicDBObject query, MongoTemplate mongoTemplate) {
		BasicDBObject newValues = new BasicDBObject(columnName, value);
		BasicDBObject set = new BasicDBObject("$set", newValues);
		mongoTemplate.getCollection(collectionName).updateMany(query, set);
	}

//    @ChangeSet(order = "003", id = "renameCityColumnToCorporation", author = "BGH")
//    public void renameCityColumnToCorporation(MongoTemplate mongoTemplate) {
//
//        String collectionName = mongoTemplate.getCollectionName(Corporation.class);
//
//        // EXEMPLE POUR RENOMMER UNE COLONNE
//        BasicDBObject field = new BasicDBObject("city", "ville");
//        BasicDBObject rename = new BasicDBObject("$rename", field);
//
//        mongoTemplate.getCollection(collectionName).updateMany(new BsonDocument(), rename);
//
//    }

}