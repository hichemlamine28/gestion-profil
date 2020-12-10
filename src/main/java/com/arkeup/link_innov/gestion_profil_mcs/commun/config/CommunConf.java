package com.arkeup.link_innov.gestion_profil_mcs.commun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.arkeup.link_innov.gestion_profil_mcs.commun.logging.LoggerAspect;



@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CommunConf {

    @Value("${cors.allowed.origins}")
    private String allowOrigin;
	
	@Bean(name="loggerAspect")
	public LoggerAspect loggerAspect() {
		return new LoggerAspect();
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowCredentials(true);
	    corsConfig.addAllowedOrigin(allowOrigin);
	    corsConfig.addAllowedHeader("*");
	    corsConfig.addAllowedMethod("POST");
	    source.registerCorsConfiguration("/oauth/token", corsConfig);
	    
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
	
}
