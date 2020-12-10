package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.UserType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ProfilESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProfilMongoRepository;

/**
 *
 * @author bona
 */
@Repository
public class ProfilRepositoryImpl
		extends CommonMongoToESRepositoryImpl<Profil, String, ProfilMongoRepository, ProfilESRepository>
		implements ProfilRepository {

	@Autowired
	private MongoTemplate mongoTemplateDefault;

	@Override
	public Profil getInformation(String username) {
		return this.mongoRepository.findByUsername(username);
	}

	@Override
	public String findUserNameByProfileId(String id) {
		Optional<Profil> optionalProfil = this.mongoRepository.findById(id);
		if (optionalProfil.isPresent()) {
			return optionalProfil.get().getUsername();
		}
		return null;
	}

	@Override
	public Page<Profil> getContactInformationsByIds(List<String> ids, String type, String filter, Pageable pageable) {
		filter = (filter == null) ? "" : filter;
		return (UserType.fromString(type) != null)
				? this.mongoRepository.findAllById(ids, UserType.fromString(type), filter, pageable)
				: this.mongoRepository.findAllById(ids, filter, pageable);
	}

	@Override
	public List<Profil> getProfilsInformationsByIds(List<String> userIds) {
		return mongoRepository.findAllByUsernameIn(userIds);
	}

	@Override
	public Page<Profil> getProfilsInformationsByIds(List<String> userIds, Pageable pageable) {
		return mongoRepository.findAllByUsernameIn(userIds, pageable);
	}

	@Override
	public Page<Profil> getNewSubscribedUsers(List<String> admins, Pageable pageable) {
		return mongoRepository.findTop20ByUsernameNotInOrderByCreationDateDesc(admins, pageable);
	}

	@Override
	public Page<Profil> getProfilsInformationsByIds(List<String> ids, String filter, String categorie,
			Pageable pageable) {
		if (StringUtils.isEmpty(categorie)) {
			return this.mongoRepository.findAllByUsername(ids, filter, pageable);
		}
		return mongoRepository.findAllByUsername(ids, filter, categorie, pageable);
	}

	@Override
	public Boolean isExistMail(String mail) {
		Optional<Profil> optionalProfil = mongoRepository.findByEmail(mail);
		return optionalProfil.isPresent();
	}

	@Override
	public List<Profil> findAll() {
		return mongoRepository.findAll();
	}

	@Override
	public List<Profil> findByFirstName(String firstName) {
		return mongoRepository.findByfirstname(firstName);
	}

	@Override
	public List<Profil> getBykeyValidateProfil(String keyValidateProfil) {

		Query query = new Query();
		query.addCriteria(Criteria.where("keyValidateProfil").regex(keyValidateProfil));

		List<Profil> profileResultBykeyValidation = mongoTemplateDefault.find(query, Profil.class);
		return profileResultBykeyValidation;
	}

	@Override
	public Collection<? extends Profil> findByFirstNameConcatLastName(String concat) {
		String firstname = null;
		String lastname = null;

		Query query = new Query();

		if (concat.contains(" ")) {
			String[] arrOfStr = concat.split(" ");
			firstname = arrOfStr[0];
			lastname = arrOfStr[1];
			// Yosra
			// Bouyacoub
		}
//		if (!firstname.isEmpty() && !lastname.isEmpty()) {
		if (!StringUtils.isBlank(firstname) && !StringUtils.isBlank(lastname)) {
			query.addCriteria(Criteria.where("firstname").regex(firstname)
					.andOperator(Criteria.where("lastname").regex(lastname)));

			List<Profil> profileResult = mongoTemplateDefault.find(query, Profil.class);
			return profileResult;
		}
		if (!StringUtils.isBlank(lastname)) {
			query.addCriteria(Criteria.where("lastname").regex(firstname));

			List<Profil> profileResult = mongoTemplateDefault.find(query, Profil.class);
			return profileResult;

		}

		query.addCriteria(Criteria.where("firstname").regex(concat));

		List<Profil> profileResult = mongoTemplateDefault.find(query, Profil.class);
		return profileResult;

	}

	@Override
	public Collection<? extends Profil> findLastCreatedProfil() {
		Date endDate = DateUtils.addDays(new Date(), -7);
		Query query = new Query();

		query.addCriteria(Criteria.where("creationDate").gte(endDate).lte(new Date()));

		List<Profil> profils = mongoTemplateDefault.find(query, Profil.class);
		return profils;
	}
}
