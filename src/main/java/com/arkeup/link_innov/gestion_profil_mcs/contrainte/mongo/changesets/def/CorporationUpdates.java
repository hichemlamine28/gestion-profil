package com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.def;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;

@Component
@ChangeLog(order = "001")
public class CorporationUpdates {

	private String collectionName;

	@ChangeSet(order = "001", id = "updateTypeId", runAlways = true, author = "Njaka")
	public void updateTypeId(MongoTemplate mongoTemplate) {
		collectionName = mongoTemplate.getCollectionName(Corporation.class).toLowerCase();
		String columnName = "type._id";
		String value = "uuid-category-industriel";
		BasicDBObject query = new BasicDBObject();
		query.put(columnName, "uuid-category-entreprise");
		updateField(columnName, value, query, mongoTemplate);

	}

	@ChangeSet(order = "002", id = "updateTypeName", runAlways = true, author = "Njaka")
	public void updateTypeName(MongoTemplate mongoTemplate) {
		String columnName = "type.name";
		String value = "Industriel";
		BasicDBObject query = new BasicDBObject();
		query.put(columnName, "Entreprise");
		updateField(columnName, value, query, mongoTemplate);

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
//    @ChangeSet(order = "002", id = "renameCityColumnToCorporation", author = "BGH")
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