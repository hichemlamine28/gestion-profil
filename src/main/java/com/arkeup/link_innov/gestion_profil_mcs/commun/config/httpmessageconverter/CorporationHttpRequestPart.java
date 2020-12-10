package com.arkeup.link_innov.gestion_profil_mcs.commun.config.httpmessageconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.corporation.CorporationDTORequestPartHttpMessageConverter;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group.GroupDTORequestPartHttpMessageConverter;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.inscription.InscriptionDTORequestPartHttpMessageConverter;

@Configuration
public class CorporationHttpRequestPart {

	@Autowired
	private CorporationDTORequestPartHttpMessageConverter corporationDTORequestPartHttpMessageConverter;
	
	@Autowired
	private GroupDTORequestPartHttpMessageConverter groupDTORequestPartHttpMessageConverter;
	
	@Autowired
	private InscriptionDTORequestPartHttpMessageConverter inscriptionDTORequestPartHttpMessageConverter;
	
	@Bean
	public HttpMessageConverters additionalConverters() {
		return new HttpMessageConverters(corporationDTORequestPartHttpMessageConverter, 
				groupDTORequestPartHttpMessageConverter, 
				inscriptionDTORequestPartHttpMessageConverter);
	}

}