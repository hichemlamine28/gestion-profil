package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.notification;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.AdminActionEnum;

public interface NotificationSA {

	public void sendNewPPHAction(String userName) ;

	public void sendNewPMAction(String userName, String idPersonneMorale);

	public void sendSkillRecommandation(String userid, String recommandationId, String recommandationType);

	public void sendAttachPMAction(String userid, String corporationId, String attachPerson, String type);

	/**
	 * @param userId
	 * @param corporationId
	 * @param adminActionEnum
	 * @param admins
	 */
	public void sendAdminCorporationAction(String userId, String corporationId, AdminActionEnum adminActionEnum, String... admins);

}
