package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistoryActions;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.UserHistoryMongoRepository;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {

	@Autowired
	private UserHistoryMongoRepository userHistoryRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserHistoryServiceImpl.class);

	@Override
	public UserHistory create(String actionDate, List<UserHistoryActions> actions) {

		return userHistoryRepository.save(new UserHistory(actionDate, actions));
	}

	@Override
	public List<UserHistory> getAll() {
		return userHistoryRepository.findAll();
	}

	@Override
	public List<UserHistory> getAllByDate(String actionDate) {
		return userHistoryRepository.findByactionDate(actionDate);
	}

	@Override
	public UserHistory update(String actionDate, List<UserHistoryActions> actions) {
		List<UserHistory> p = userHistoryRepository.findByactionDate(actionDate);
		UserHistory history = p.get(0);
		history.setActions(actions);
		return userHistoryRepository.save(history);
	}

	@Override
	public void addOrUbdateHistory(String userID, String actionName, String actionId) {
		String pattern = "dd/MM/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();

		// Today
		String historyDate = df.format(today);

		// Get History by date
		List<UserHistory> historiesAll = getAllByDate(historyDate);

		if (historiesAll.isEmpty() || historiesAll == null) {
			// if we don't have UserHistory so create a new UserHistory
			Map<String, Integer> userIDs = new HashMap<>();
			userIDs.put(userID, 1);
			UserHistoryActions action = new UserHistoryActions(actionId, actionName, userIDs);
			List<UserHistoryActions> actions = new ArrayList<>();
			actions.add(action);
			// Create a new UserHistory
			create(historyDate, actions);
			LOGGER.info("Create new UserHistory date :" + historyDate + " The first Action Name :" + actionName);
		} else {
			// We have one UserHistory in this day so update the existing UserHistory
			// Get UserHistory
			UserHistory existingHistory = historiesAll.get(0);
			// Get all actions
			List<UserHistoryActions> actions = existingHistory.getActions();
			boolean isnew = true;
			for (UserHistoryActions userHistoryActions : actions) {
				if (userHistoryActions.getActionName().equals(actionName)) {
					// Action name exist so update the user list
					// Get existing User IDs
					Map<String, Integer> ExistinguserIDs = userHistoryActions.getUserId();
					updateOccurence(userID, ExistinguserIDs);
					// Aliment the new list of users
					userHistoryActions.setUserId(ExistinguserIDs);
					update(historyDate, actions);
					isnew = false;
					LOGGER.info("Update existing Action for UserHistory date :" + historyDate + " Action Name :"
							+ actionName);
					break;
				}
			}
			if (isnew) {
				// Action name note exist
				// Create new action
				UserHistoryActions newAction = new UserHistoryActions();
				Map<String, Integer> userIDs = new HashMap<>();
				userIDs.put(userID, 1);
				// Add Users to the new action
				newAction.setUserId(userIDs);
				// Set action name
				newAction.setActionName(actionName);
				newAction.setActionId(actionId);
				actions.add(newAction);
				// Udate history
				update(historyDate, actions);
				LOGGER.info("Create new Action for UserHistory date :" + historyDate + " Action Name :" + actionName);
			}

		}
	}

	private void updateOccurence(String userID, Map<String, Integer> ExistinguserIDs) {
		boolean userisNew = true;

		for (Map.Entry<String, Integer> entry : ExistinguserIDs.entrySet()) {

			if ((entry.getKey()).equals(userID)) {
				LOGGER.info("Update the occurence value of  User : " + entry.getKey());
				ExistinguserIDs.replace(entry.getKey(), entry.getValue() + 1);
				userisNew = false;
				break;
			}

		}

		if (userisNew) {
			ExistinguserIDs.put(userID, 1);
		}
	}

}