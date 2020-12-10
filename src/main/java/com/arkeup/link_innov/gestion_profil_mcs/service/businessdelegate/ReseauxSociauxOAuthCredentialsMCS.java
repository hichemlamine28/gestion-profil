/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.feign.FeignClientConfiguration;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;

/**
 * Créer temporairement cette interface pour debloquer Stephan.
 * En attendant une solution pour l'envoie de token de type password entre les MCS
 * 
 * @author mikajy
 *
 */
@FeignClient(
		name = "ReseauxSociauxClientCredentials",
		url = "#{'${reseau-social.mcs.url}'}"
	)
public interface ReseauxSociauxOAuthCredentialsMCS {
	/**
	 * Créer un noeud user dans le mcs reseau social
	 *
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/user/create")
	ReseauSocialUserDTO createUser(@RequestBody ReseauSocialUserDTO userDTO);

}
