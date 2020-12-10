package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.donnee.LinkInnovCommunication;
import com.arkeup.link_innov.donnee.event.action.AdminCorporationAction;
import com.arkeup.link_innov.donnee.event.action.AttachPMAction;
import com.arkeup.link_innov.donnee.event.action.NewPMAction;
import com.arkeup.link_innov.donnee.event.action.NewPPHAction;
import com.arkeup.link_innov.donnee.event.action.RecommandationAction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.AdminActionEnum;
import com.arkeup.link_innov.gestion_profil_mcs.service.technique.rabbitmq.RabbitMqCommunicationST;

@Service
public class NotificationSAImpl implements NotificationSA {

	@Autowired
	private RabbitMqCommunicationST rabbitMqCommunicationST;

	@Override
	public void sendNewPPHAction(String userName) {

		LinkInnovCommunication linkInnovCommunication = new LinkInnovCommunication();
		NewPPHAction newPPHAction =  new NewPPHAction();

		newPPHAction.setEventUuid(UUID.randomUUID().toString());
		newPPHAction.setSenderId(userName);
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Z")));//ZoneOffset.UTC
		newPPHAction.setEventTimestamp(now.getTimeInMillis()/1000);
		linkInnovCommunication.setLinkInnovEvent(newPPHAction);

		rabbitMqCommunicationST.sendMessageAction(linkInnovCommunication);
	}

	@Override
	public void sendNewPMAction(String userName, String idPersonneMorale) {

		LinkInnovCommunication linkInnovCommunication = new LinkInnovCommunication();
		NewPMAction newPMAction =  new NewPMAction();

		newPMAction.setEventUuid(UUID.randomUUID().toString());
		newPMAction.setSenderId(userName);
		newPMAction.setIdPersonneMorale(idPersonneMorale);
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Z")));//ZoneOffset.UTC
		newPMAction.setEventTimestamp(now.getTimeInMillis()/1000);
		linkInnovCommunication.setLinkInnovEvent(newPMAction);

		rabbitMqCommunicationST.sendMessageAction(linkInnovCommunication);

	}

	@Override
	public void sendSkillRecommandation(String userid, String recommandationId, String recommandationType) {

		LinkInnovCommunication linkInnovCommunication = new LinkInnovCommunication();
		RecommandationAction recommandationAction = new RecommandationAction();

		recommandationAction.setRecommandationId(recommandationId);
		recommandationAction.setRecommandationType(recommandationType);
		recommandationAction.setSenderId(userid);
		recommandationAction.setEventUuid(UUID.randomUUID().toString());
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Z")));
		recommandationAction.setEventTimestamp(now.getTimeInMillis()/1000);
		linkInnovCommunication.setLinkInnovEvent(recommandationAction);

		rabbitMqCommunicationST.sendMessageAction(linkInnovCommunication);

	}

	@Override
	public void sendAttachPMAction(String userid, String corporationId, String attachPerson, String type) {

		LinkInnovCommunication linkInnovCommunication = new LinkInnovCommunication();
		AttachPMAction attachPMAction = new AttachPMAction();

		attachPMAction.setCorporationId(corporationId);
		attachPMAction.setAttachPerson(attachPerson);
		attachPMAction.setSenderId(userid);
		attachPMAction.setType(type);
		attachPMAction.setEventUuid(UUID.randomUUID().toString());
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Z")));
		attachPMAction.setEventTimestamp(now.getTimeInMillis()/1000);
		linkInnovCommunication.setLinkInnovEvent(attachPMAction);

		rabbitMqCommunicationST.sendMessageAction(linkInnovCommunication);

	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification.NotificationSA#sendAdminCorporationAction(java.lang.String, java.lang.String, java.util.List, com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.AdminActionEnum)
	 */
	@Override
	public void sendAdminCorporationAction(String userId, String corporationId, AdminActionEnum adminActionEnum, String... admins) {
		LinkInnovCommunication linkInnovCommunication = new LinkInnovCommunication();
		AdminCorporationAction adminCorporationAction = new AdminCorporationAction();

		adminCorporationAction.setSenderId(userId);
		adminCorporationAction.setCorporationId(corporationId);
		adminCorporationAction.setAdminAction(adminActionEnum.toString());

		if(admins != null && admins.length > 0) {
			adminCorporationAction.setAdmins(Arrays.asList(admins));
		}

		adminCorporationAction.setEventUuid(UUID.randomUUID().toString());
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Z")));
		adminCorporationAction.setEventTimestamp(now.getTimeInMillis()/1000);
		linkInnovCommunication.setLinkInnovEvent(adminCorporationAction);

		rabbitMqCommunicationST.sendMessageAction(linkInnovCommunication);
	}
}
