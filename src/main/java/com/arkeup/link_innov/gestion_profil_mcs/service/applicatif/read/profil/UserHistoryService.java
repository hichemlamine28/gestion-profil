package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;

public interface UserHistoryService {

	public UserHistory create(String actionDate, List<UserHistoryActions> actions);

	List<UserHistory> getAll();

	List<UserHistory> getAllByDate(String actionDate);

	UserHistory update(String actionDate, List<UserHistoryActions> actions);

	void addOrUbdateHistory(String userID, String actionName, String actionId);

//	UserHistory getByFirstName(String firstName);

//	UserHistory update(String firstName, String lastName, int age);

//	void deleteAll();

//	void delete(String firstName);
}
