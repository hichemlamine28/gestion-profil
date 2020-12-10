package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.authorization_server.MongoJwtTokenStore;

@Configuration
public class JwtConfig {
	
	@Value("${security.signing-key}")
	private String signingKey;
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}

	@Bean
	public MongoJwtTokenStore mongoJwtTokenStore() {
	    return new MongoJwtTokenStore(accessTokenConverter());
	}

	@Bean
	@Primary //Making this primary to avoid any accidental duplication with another token service instance of the same name
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(mongoJwtTokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}
}
