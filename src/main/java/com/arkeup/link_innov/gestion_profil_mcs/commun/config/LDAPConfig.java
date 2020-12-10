package com.arkeup.link_innov.gestion_profil_mcs.commun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 *https://www.baeldung.com/spring-data-ldap
 *
 */
@Configuration
@EnableLdapRepositories(basePackages = "com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap")
public class LDAPConfig {


    @Value("${ldap.url}")
    String ldapUrl;
    @Value("${ldap.userDn}")
    String ldapUserDn;
    @Value("${ldap.password}")
    String ldapPassword;
    @Value("${ldap.base}")
    String ldapBase;
    @Value("${ldap.clean}")
    String ldapClean;
    @Value("${ldap.directory.type}")
    String ldapdDrectoryType;


    @Bean
    public ContextSource contextSource() {

        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(ldapUrl);
        ldapContextSource.setBase(ldapBase);
        ldapContextSource.setUserDn(ldapUserDn);
        ldapContextSource.setPassword(ldapPassword);

        return ldapContextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }

}
