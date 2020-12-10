/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.RabbitMQUserDTO;

/**
 * @author mikajy
 *
 */
public interface RabbitMQUserDTOFactory {
	
	/**
	 * 
	 * @return
	 */
	RabbitMQUserDTO getInstance();
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	RabbitMQUserDTO getInstance(String userId);
}
