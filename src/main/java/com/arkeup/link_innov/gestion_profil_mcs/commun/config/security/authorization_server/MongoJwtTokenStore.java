package com.arkeup.link_innov.gestion_profil_mcs.commun.config.security.authorization_server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.oauth2.OAuth2AccessTokenDO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.oauth2.OAuth2RefreshTokenDO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.user_auth.UserAuthCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2.OAuth2AccessTokenDOMongoRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2.OAuth2RefreshTokenDOMongoRepository;


public class MongoJwtTokenStore  extends JwtTokenStore{
	
	@Autowired
	private OAuth2AccessTokenDOMongoRepository oAuth2AccessTokenDOMongoRepository;
	
	@Autowired
	private OAuth2RefreshTokenDOMongoRepository oAuth2RefreshTokenDOMongoRepository;
	
	@Autowired
	private UserAuthCUDSM userAuthCUDSM;
	
	public static final String TOKEN_ID = AccessTokenConverter.JTI;
	
	public MongoJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
		super(jwtTokenEnhancer);
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		
		if (token != null && token.getValue() != null) {
			String accessToken = token.getValue();
			String refreshToken = token.getRefreshToken().getValue();
			String jti = token.getAdditionalInformation().get(TOKEN_ID).toString();
			OAuth2AccessTokenDO auth2AccessTokenDO = null;
			
			if (authentication.getUserAuthentication() != null) {
				
				UserDetails user = (UserDetails) authentication.getUserAuthentication().getPrincipal();
				String userName = user.getUsername();
				
				auth2AccessTokenDO = new OAuth2AccessTokenDO(jti, accessToken, userName);
				OAuth2RefreshTokenDO auth2RefreshTokenDO = new OAuth2RefreshTokenDO(jti, refreshToken, userName);
				
				oAuth2AccessTokenDOMongoRepository.save(auth2AccessTokenDO);
				oAuth2RefreshTokenDOMongoRepository.save(auth2RefreshTokenDO);
			}
			else {
				auth2AccessTokenDO = new OAuth2AccessTokenDO(jti, accessToken, null);
				oAuth2AccessTokenDOMongoRepository.save(auth2AccessTokenDO);
			}
			
		}
		
	}
	
	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		
		OAuth2AccessToken accessToken = super.readAccessToken(tokenValue);
		if(accessToken.isExpired()) {
			return accessToken;
		}
		
		OAuth2AccessTokenDO auth2AccessTokenDO = oAuth2AccessTokenDOMongoRepository.findOneByAccessToken(accessToken.getValue());
		
		if (auth2AccessTokenDO != null) {
			userAuthCUDSM.userAuthLastConnexion(auth2AccessTokenDO.getUsername());
			return accessToken;
		}
		
		return null;
		
	}
	
	
	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		
		OAuth2RefreshTokenDO auth2RefreshTokenDO = oAuth2RefreshTokenDOMongoRepository.findOneByRefreshToken(tokenValue);
		if (auth2RefreshTokenDO != null) {
			
			SimpleDateFormat toUtc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	        toUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			
			try {
				Date lastConnexion = userAuthCUDSM.getDateOflastConnexion(auth2RefreshTokenDO.getUsername());
				if (lastConnexion != null) {
					Date lastActivityDate = f.parse(toUtc.format(lastConnexion));
					Date now = f.parse(toUtc.format(new Date()));
					
					long diff = now.getTime() - lastActivityDate.getTime();
					long diffHours = diff / (60 * 60 * 1000) % 24;
					
					if (diffHours >= 1) { return null; }
					else { return super.readRefreshToken(tokenValue); }
				}
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		OAuth2Authentication oAuth2Authentication = null;
		OAuth2Request oAuth2Request = null;
		Boolean isAuthenticated = true;
		
		@SuppressWarnings("unchecked")
		List<String> grantedAuthorities = (List<String>) token.getAdditionalInformation().get("authorities");
		HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if (grantedAuthorities != null) {
			for (String grantedAuthority : grantedAuthorities) {
				authorities.add(new SimpleGrantedAuthority(grantedAuthority));
			}
		}
		else authorities.add(new SimpleGrantedAuthority("DEFAULT"));
		String userPrinsipal = (String) token.getAdditionalInformation().get("user_name");
		if (userPrinsipal == null) userPrinsipal ="defaultUser";
		
		oAuth2Request = new OAuth2Request(null, userPrinsipal, authorities, true, null, null, null, null, null);
		
        User principal = new User(userPrinsipal, "", authorities);
        List<GrantedAuthority> listGrantedAuthority = new ArrayList<GrantedAuthority>(authorities);
        TestingAuthenticationToken userAuthentication = new TestingAuthenticationToken(principal, null, listGrantedAuthority);
        userAuthentication.setAuthenticated(isAuthenticated);
        
        oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
		
        return oAuth2Authentication;
	}


}
