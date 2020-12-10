/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.InscriptionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;

/**
 * @author mikajy
 *
 */
public interface ProfilFactory {
	/**
	 * 
	 * @return
	 */
	ProfilDTO getDTOInstance();
	
	/**
	 * 
	 * @param userAuthDTO
	 * @return
	 */
	ProfilDTO getDTOInstance(SignUpDTO signUpDTO);
	
	/**
	 * 
	 * @param signUpDTO
	 * @return
	 */
	Profil getEntityInstance(SignUpDTO signUpDTO);
	
	Profil getEntityInstance(InscriptionDTO inscriptionDTO);
}
