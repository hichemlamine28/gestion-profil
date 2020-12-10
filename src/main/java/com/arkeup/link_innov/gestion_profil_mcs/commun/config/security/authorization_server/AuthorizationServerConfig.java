package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.authorization_server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.user_details.UserDetailsConfig;

/**
 * Created by bgh on 18/10/18.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;

	@Autowired
	private MongoJwtTokenStore mongoJwtTokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsConfig userDetailsService;

//	private final int REFRESHTOKENVALIDITYSECONDS = 60 * 60 * 24 * 30;// one month
//	private final int ACCESSTOKENVALIDITYSECONDS = 60 * 60 * 24 * 2; // two day

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer
		        .inMemory()
		        .withClient(clientId)
				.secret(passwordEncoder.encode(clientSecret))
				.authorizedGrantTypes("refresh_token", "password","client_credentials") 
		        .scopes("read", "write")
		        .resourceIds(resourceIds)
		        .accessTokenValiditySeconds(60 * 60 * 24 * 2)
		        .refreshTokenValiditySeconds(60 * 60 * 24 * 30);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(),accessTokenConverter));
		endpoints.tokenStore(mongoJwtTokenStore)
				.userDetailsService(userDetailsService)
		        .accessTokenConverter(accessTokenConverter)
		        .tokenEnhancer(enhancerChain)
		        .authenticationManager(authenticationManager);
	}
}
