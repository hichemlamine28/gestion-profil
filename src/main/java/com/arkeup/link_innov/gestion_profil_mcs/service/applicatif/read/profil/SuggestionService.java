package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Suggestion;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SuggestionDTO;

public interface SuggestionService {

	void create(SuggestionDTO suggestionDTO);

	List<Suggestion> update(SuggestionDTO suggestionDTO);

	void saveSuggestions(String ownerId);

	List<Suggestion> getAllInvitationSuggestions(String idOwner);

	List<Suggestion> getAllSubscribeSuggestions(String idOwner);

}
