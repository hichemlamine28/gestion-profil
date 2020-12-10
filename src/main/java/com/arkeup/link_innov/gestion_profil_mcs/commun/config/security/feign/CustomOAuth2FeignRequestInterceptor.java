package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class CustomOAuth2FeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";
    private static final String BASIC_TOKEN_TYPE = "Basic";
    private static final String TOKEN_VALUE_KEY = "OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomOAuth2FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {

        String jwt = null;
        // first way to get jwt token
//		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		if (requestAttributes == null) return;
//		HttpServletRequest request = requestAttributes.getRequest();
//		if (request == null) return;
//		
//		jwt = request.getAttribute(TOKEN_VALUE_KEY).toString();

        // seconde way to get jwt token
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return;
        }
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        if (details == null) {
            return;
        }
        jwt = details.getTokenValue();
        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, jwt));
    }
}
