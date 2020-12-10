package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Suggestion;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.neo4j.User;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SuggestionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.OtherProductionRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.SuggestionMongoRepository;

import io.netty.util.internal.StringUtil;

@Service
public class SuggestionServiceImpl implements SuggestionService {

//	private UserHistoryMongoRepository userHistoryRepository;

	@Autowired
	private SuggestionMongoRepository suggestionMongoRepository;

	@Autowired
	private MongoTemplate mongoTemplateDefault;

	@Autowired
	private OtherProductionRepository otherProductionRepository;

	@Autowired
	private ProfilRSA profilRSA;

	private static final Logger LOGGER = LoggerFactory.getLogger(SuggestionServiceImpl.class);

	private static final String USER_NAME = "username";

	private static final String EMAIL = "email";

	@Override
	public void create(SuggestionDTO suggestionDTO) {

		// Convert DTO TO entity
		saveSuggestions(suggestionDTO.getUsername());
	}

	// TO save all suggestion by ownerID
	@Override
	public void saveSuggestions(String ownerId) {

		// get all authors from other productions
		List<User> authors = getAuthersFromAllOtherProduction(ownerId);

		List<Profil> inviteUserNameSuggestion = new ArrayList<Profil>();

		List<String> subscribeUserNameSuggestion = new ArrayList<String>();

		if (authors != null) {
			authors.forEach(author -> {
//				List<Profil> p = profilRSA.getListProfilByFirstName(author);

				List<Profil> p = profilRSA.getListProfilByFirstNameConcatLastName(author);
				if (p != null && !p.isEmpty()) {

					inviteUserNameSuggestion.addAll(p);
				}
				if (p.isEmpty() || p == null) {
					subscribeUserNameSuggestion.add(author.getFirstName());
				}
			});
			saveveInvitationSuggestions(inviteUserNameSuggestion, ownerId);
			saveveSubscribeSuggestions(subscribeUserNameSuggestion, ownerId);
		}
	}

	// Get suggestion invitation list (suggestions has username and email not null)
	@Override
	public List<Suggestion> getAllInvitationSuggestions(String idOwner) {

		Query query = new Query();
		query.addCriteria(Criteria.where(USER_NAME).regex(idOwner).andOperator(Criteria.where(EMAIL).ne(null)));

		List<Suggestion> suggestionsResults = new ArrayList<>();
		mongoTemplateDefault.find(query, Suggestion.class).forEach(suggest -> {
			if (!suggest.getIsInvited())
				suggestionsResults.add(suggest);
		});
		return suggestionsResults;
	}

	// Get suggestion subscribe list (suggestions has username and email is null)
	@Override
	public List<Suggestion> getAllSubscribeSuggestions(String idOwner) {

		Query query = new Query();
		query.addCriteria(Criteria.where(USER_NAME).regex(idOwner).andOperator(Criteria.where(EMAIL).is(null)));

		List<Suggestion> suggestionsResults = new ArrayList<>();
		mongoTemplateDefault.find(query, Suggestion.class).forEach(suggest -> {
			if (!suggest.getIssubscribed())
				suggestionsResults.add(suggest);
		});
		return suggestionsResults;
	}

	@Override
	public List<Suggestion> update(SuggestionDTO suggestionDTO) {
		Query query = new Query();
		if (!StringUtil.isNullOrEmpty(suggestionDTO.getUsername())
				&& !StringUtil.isNullOrEmpty(suggestionDTO.getEmail()))
			query.addCriteria(Criteria.where(USER_NAME).regex(suggestionDTO.getUsername())
					.andOperator(Criteria.where(EMAIL).is(suggestionDTO.getEmail())));
		else
			query.addCriteria(Criteria.where(USER_NAME).regex(suggestionDTO.getUsername())
					.andOperator(Criteria.where("firstname").is(suggestionDTO.getFirstname())));

		List<Suggestion> suggestionsResults = mongoTemplateDefault.find(query, Suggestion.class);
		suggestionsResults.forEach(suggestion -> {
			if (!StringUtil.isNullOrEmpty(suggestion.getEmail()))
				suggestion.setIsInvited(true);
			else
				suggestion.setIssubscribed(true);
			mongoTemplateDefault.save(suggestion);
		});
		return suggestionsResults;
	}

	private void saveveInvitationSuggestions(List<Profil> profils, String ownerId) {
		if (profils != null && !profils.isEmpty()) {
			profils.forEach(profil -> {
				if (!isSuggestionExist(Suggestion.suggestionFromProfils(profil, ownerId)))
					suggestionMongoRepository.save(Suggestion.suggestionFromProfils(profil, ownerId));
			});
		}
	}

	private void saveveSubscribeSuggestions(List<String> names, String ownerId) {
		if (names != null && !names.isEmpty()) {
			names.forEach(name -> {
				Suggestion suggestion = new Suggestion();
				suggestion.setFirstname(name);
				suggestion.setUsername(ownerId);

				if (!isSuggestionExist(suggestion))
					suggestionMongoRepository.save(suggestion);
			});
		}

	}

	private List<User> getAuthersFromAllOtherProduction(String ownerId) {

		List<OtherProduction> otherProductions = otherProductionRepository.findByOwnerId(ownerId);

		List<User> suggerUsers = new ArrayList<>();
		if (otherProductions != null) {
			otherProductions.forEach(othP -> {
				suggerUsers.addAll(othP.getAuthors());
			});
		}

		return suggerUsers;
	}

	private boolean isSuggestionExist(Suggestion suggestion) {

		Query query = new Query();
		// verify if invitation suggestion exist
		if (suggestion != null && !StringUtil.isNullOrEmpty(suggestion.getEmail())
				&& !StringUtil.isNullOrEmpty(suggestion.getFirstname())) {
			query.addCriteria(Criteria.where(USER_NAME).regex(suggestion.getUsername())
					.andOperator(Criteria.where(EMAIL).regex(suggestion.getEmail())));
		}

		// verify if subscribe suggestion exist
		if (suggestion != null && StringUtil.isNullOrEmpty(suggestion.getEmail())
				&& !StringUtil.isNullOrEmpty(suggestion.getFirstname())) {
			query.addCriteria(Criteria.where(USER_NAME).regex(suggestion.getUsername())
					.andOperator(Criteria.where("firstname").regex(suggestion.getFirstname())));
		}

		List<Suggestion> suggestionsResults = mongoTemplateDefault.find(query, Suggestion.class);
		if (!suggestionsResults.isEmpty())
			return true;
		else
			return false;
	}
}