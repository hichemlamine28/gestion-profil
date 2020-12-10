/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.feign.FeignClientConfiguration;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.MailParametersDTO;

/**
 * @author mikajy
 * 
 * A Client interface to communicate with GestionNotificationMCS.
 *
 */
@FeignClient(
	name = "NotificationMCSClient", 
	url = "#{'${notification.mcs.url}'}/notification"
)
public interface NotificationMCS {
	
	@PostMapping(path = "/mails")
	public MailParametersDTO sendEmail(@RequestBody MailParametersDTO mailParametersDTO);

}
