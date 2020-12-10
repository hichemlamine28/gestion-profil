/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//import com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.feign.FeignClientConfiguration;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.RabbitMQUserDTO;

/**
 * @author mikajy
 * 
 * Interface pour communiquer avec le microservice gestion-rabbitmqusers-mcs.
 *
 */
@FeignClient(name = "RabbitMQUsersMCSClient", url = "#{'${rabbitmq.mcs.url}'}")
public interface RabbitMQUsersMCS {
	
	/**
	 * Get user Credential
	 * 
	 * @param token
	 * @param userId
	 * @return
	 */
	@GetMapping(path="/rabbitmqUser/getCredentials", consumes=MediaType.APPLICATION_JSON_VALUE)
	RabbitMQUserDTO getCredentials(@RequestParam("userId") String username);
	
	/**
	 * Create user Credential 
	 *  
	 * @param token
	 * @param rabbitMQUserDTO
	 * @return
	 */
	@PostMapping(path="/rabbitmqUser/create", consumes=MediaType.APPLICATION_JSON_VALUE)
	RabbitMQUserDTO createUser(@RequestBody RabbitMQUserDTO rabbitMQUserDTO);
	
	/**
	 * Delete user
	 * 
	 * @param token
	 * @param username
	 * @return
	 */
	@DeleteMapping(path="/rabbitmqUser/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
	RabbitMQUserDTO deleteUser(@RequestParam("userId") String userId);
	
	
}
