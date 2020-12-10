package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.authorization_server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.user_details.CustomUserDetails;



@Component
public class CustomTokenEnhancer implements TokenEnhancer {
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	
    	Authentication userAuthentication = authentication.getUserAuthentication();
    	if (userAuthentication != null) {
    		CustomUserDetails userDetails = (CustomUserDetails) userAuthentication.getPrincipal();
    		
    		String openFireId = userDetails.getOpenFireId(); 
    		
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("openfire_id",openFireId);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
           
		}
    	return accessToken;
    }
}