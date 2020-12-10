/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.RabbitMQUserDTO;

/**
 * @author mikajy
 *
 */
@Component
public class RabbitMQUserDTOFactoryImpl implements RabbitMQUserDTOFactory {

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RabbitMQUserDTOFactory#getInstance()
	 */
	@Override
	public RabbitMQUserDTO getInstance() {
		return new RabbitMQUserDTO();
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RabbitMQUserDTOFactory#getInstance(java.lang.String)
	 */
	@Override
	public RabbitMQUserDTO getInstance(String userId) {
		RabbitMQUserDTO rabbitMQUserDTO = new RabbitMQUserDTO();
		rabbitMQUserDTO.setUserId(userId);
		rabbitMQUserDTO.setTags("user");
		return rabbitMQUserDTO;
	}

}
