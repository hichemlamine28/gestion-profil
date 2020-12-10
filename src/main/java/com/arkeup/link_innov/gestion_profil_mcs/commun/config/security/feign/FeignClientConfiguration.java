///**
// * 
// */
//package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.feign;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
//import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
//import org.springframework.security.oauth2.common.AuthenticationScheme;
//
//import feign.Logger;
//import feign.RequestInterceptor;
//
///**
// * @author mikajy
// *
// */
//public class FeignClientConfiguration {
//	
//	@Value("${security.jwt.client-id}")
//	private String clientId;
//
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//	
//	@Value("${security.jwt.accesstoken.uri}")
//	private String accessTokenUri;
//	
//	@Bean
//    public RequestInterceptor requestInterceptor() {
//		OAuth2FeignRequestInterceptor requestInterceptor = new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
//		// grant_type=client_credentials
////		requestInterceptor.setAccessTokenProvider(new ClientCredentialsAccessTokenProvider());
//		return requestInterceptor;
//    }
//	
//	@Bean
//    public Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//    private OAuth2ProtectedResourceDetails resource() {
//        final ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
//        details.setAccessTokenUri(accessTokenUri);
//        details.setAuthenticationScheme(AuthenticationScheme.header);
//        details.setClientId(clientId);
//        details.setClientSecret(clientSecret);
//        details.setGrantType("client_credentials");
//        details.setScope(Arrays.asList("read", "write"));
//        return details;
//    }
//}
