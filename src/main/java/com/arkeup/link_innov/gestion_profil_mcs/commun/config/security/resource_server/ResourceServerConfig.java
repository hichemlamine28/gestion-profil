package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.resource_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * Created by bgh on 18/10/18.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceIds);
		resources.tokenServices(tokenServices);
	}

	@Override
    public void configure(HttpSecurity http) throws Exception {
    	http
    	.requestMatchers()
    	.and()
    	.authorizeRequests()
    	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
    	.antMatchers("/actuator/**").permitAll()
//    	.antMatchers("/v2/api-docs").authenticated()
    	.antMatchers("/profil/setProfilHasMedia/anonymous","/category/listCategories","/dynamic/page/public/**", "/inscription/importBetaTest/anonymous",
    			     "/account/validation/choosePassword", "/account/passwordRecovery", "/signup",
					 "/registration-checkout/**", "/rg-extract",
    			     "/profil/information/public/**", "/profil/last/subscribed/users","/inscription/re-send-mail-validate/*",
    			     "/referential/activitysector/list", "/account/validation/","/authentification/validation/*").authenticated();
    }

}
