package com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.def;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ChangeLog(order = "001")
public class ProfilUpdates {

	private String collectionName;

	@ChangeSet(order = "001", id = "addMaleToProfil", runAlways = true, author = "Njaka")
	public void addMaleToProfil(MongoTemplate mongoTemplate) {
		collectionName = mongoTemplate.getCollectionName(Profil.class).toLowerCase();
		String columnName = "male";
		boolean value = true;
		addField(columnName, value, mongoTemplate);

	}

	@ChangeSet(order = "002", id = "updateCategoryId", runAlways = true, author = "Njaka")
	public void updateCategoryId(MongoTemplate mongoTemplate) {
		String columnName = "category._id";
		String value = "uuid-category-industriel";
		BasicDBObject query = new BasicDBObject();
		query.put(columnName, "uuid-category-entreprise");
		updateField(columnName, value, query, mongoTemplate);

	}

	@ChangeSet(order = "003", id = "updateCategoryName", runAlways = true, author = "Njaka")
	public void updateCategoryName(MongoTemplate mongoTemplate) {
		String columnName = "category.name";
		String value = "Industriel";
		BasicDBObject query = new BasicDBObject();
		query.put(columnName, "Entreprise");
		updateField(columnName, value, query, mongoTemplate);

	}

	@ChangeSet(order = "004", id = "updateActivityArea", runAlways = true, author = "André")
	public void updateActivityArea(MongoTemplate mongoTemplate)
	{
		List<Profil> profils = mongoTemplate.find(new Query().addCriteria(Criteria.where("activityArea").exists(true).type(4)), Profil.class);
		List<String> idProfils = profils.stream().map(Profil::getId).collect(Collectors.toList());

		if(!idProfils.isEmpty())
		{
			Update update = new Update();
			update.unset("activityArea");
			mongoTemplate.updateMulti(new Query().addCriteria(Criteria.where("_id").nin(idProfils)), update, Profil.class);
		}
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

//    @ChangeSet(order = "004", id = "renameCityColumnToCorporation", author = "BGH")
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
