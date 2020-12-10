//package com.arkeup.link_innov.gestion_profil_mcs.commun.config.httpmessageconverter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group.GroupDTORequestPartHttpMessageConverter;
//
//@Configuration
//public class GroupHttpRequestPart {
//
//	@Autowired
//	private GroupDTORequestPartHttpMessageConverter groupDTORequestPartHttpMessageConverter;
//	
//	@Bean
//	public HttpMessageConverters additionalConverters() {
//		return new HttpMessageConverters(groupDTORequestPartHttpMessageConverter);
//	}
//
//}