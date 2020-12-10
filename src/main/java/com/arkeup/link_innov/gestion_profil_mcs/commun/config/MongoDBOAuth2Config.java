package com.arkeup.link_innov.gestion_profil_mcs.commun.config;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
 
@Configuration
@EnableMongoRepositories(basePackages = "com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.oauth2",mongoTemplateRef = "mongoTemplateOAuth2")
public class MongoDBOAuth2Config {
	
    @Value("${mongodb.secure}")
    private Boolean secure;
    
    @Value("${mongodb.admin}")
    private String dbAdmin;
 
    @Bean(name = "mongoTemplateOAuth2")
    public MongoTemplate mongoTemplate(@Qualifier("mongoFactoryOAuth2") MongoDbFactory mongoFactory) throws Exception {
        return new MongoTemplate(mongoFactory);
    }
 
    @Bean("mongoFactoryOAuth2")
    public MongoDbFactory mongoFactory(@Qualifier("mongoPropertiesOAuth2") MongoProperties mongoProperties) throws Exception {
    	
    	String mongoURI = null;

    	if (secure) {
    		String mdp = new String(mongoProperties.getPassword());
    		mongoURI = MessageFormat.format( "mongodb://{0}:{1}@{2}:{3}/{4}", mongoProperties.getUsername(), mdp, mongoProperties.getHost(), mongoProperties.getPort().toString(), dbAdmin);
		}
    	else {
    		mongoURI = MessageFormat.format( "mongodb://{0}:{1}", mongoProperties.getHost(), mongoProperties.getPort().toString());
    	}
    	return new SimpleMongoDbFactory(new MongoClient( new MongoClientURI(mongoURI)),mongoProperties.getDatabase());
    }
    
 
    @Bean(name = "mongoPropertiesOAuth2")
    @ConfigurationProperties(prefix = "mongodb.oauth2")
    public MongoProperties properties() throws Exception {
        return new MongoProperties();
    }
    
    @Bean(name = "mongobeeOAuth2")
    public Mongobee mongobee(final @Qualifier("mongoPropertiesOAuth2") MongoProperties mongoProperties,
                             final @Qualifier("mongoTemplateOAuth2") MongoTemplate mongoTemplate,
                             Environment environment){
    	String mongoURI = null;
    	
    	if (secure) {
    		String mdp = new String(mongoProperties.getPassword());
    		mongoURI = MessageFormat.format( "mongodb://{0}:{1}@{2}:{3}/{4}", mongoProperties.getUsername(), mdp, mongoProperties.getHost(), mongoProperties.getPort().toString(), dbAdmin);
		}
    	else {
    		mongoURI = MessageFormat.format( "mongodb://{0}:{1}", mongoProperties.getHost(), mongoProperties.getPort().toString());
    	}
    	
    	Mongobee runner = new Mongobee(mongoURI);

        runner.setMongoTemplate(mongoTemplate);
        runner.setDbName(mongoTemplate.getDb().getName());

        runner.setSpringEnvironment(environment);


        runner.setChangeLogsScanPackage(
                "com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.oauth2"); // the package to be scanned for changesets

        return runner;
    }
 
}
