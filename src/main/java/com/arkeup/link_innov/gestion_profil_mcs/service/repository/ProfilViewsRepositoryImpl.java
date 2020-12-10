package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

//imports as static
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfilViewsCount;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ProfilViewsESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProfilViewsMongoRepository;

/**
 *
 * @author njaka
 */
@Repository
public class ProfilViewsRepositoryImpl
		extends CommonMongoToESRepositoryImpl<ProfileViews, String, ProfilViewsMongoRepository, ProfilViewsESRepository>
		implements ProfilViewsRepository {

	@Autowired
	private MongoTemplate mongoTemplateDefault;

	@Override
	public Integer getNbProfilViews(String userId) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -90);
		Date last90days = calendar.getTime();
		Aggregation aggregation = newAggregation(match(Criteria.where("userId").is(userId)), project("viewList"),
				unwind("viewList"), match(Criteria.where("viewList.seenDate").gt(last90days)),
				group("viewList").count().as("total"));
		List<ProfilViewsCount> result = mongoTemplateDefault
				.aggregate(aggregation, ProfileViews.class, ProfilViewsCount.class).getMappedResults();
		return result.size();
	}

	@Override
	public List<ProfileViews> findByUserId(String userId) {
		return this.mongoRepository.findByUserId(userId);
	}

}
