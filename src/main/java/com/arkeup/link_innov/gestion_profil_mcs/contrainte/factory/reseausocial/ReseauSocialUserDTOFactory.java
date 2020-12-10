/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;

/**
 * @author mikajy
 *
 */
public interface ReseauSocialUserDTOFactory {
	/**
	 * 
	 * @return
	 */
	ReseauSocialUserDTO getInstance();
	
	/**
	 * 
	 * @param id
	 * @param lastname
	 * @param firstname
	 * @return
	 */
	ReseauSocialUserDTO getInstance(String id, String lastname, String firstname);
	
	/**
	 * 
	 * @param profil
	 * @return
	 */
	ReseauSocialUserDTO getInstance(Profil profil);
}
