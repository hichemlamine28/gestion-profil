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
@EnableMongoRepositories(basePackages = "com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs",mongoTemplateRef = "mongoTemplateDefault")
public class MongoDBDefaultConfig {
	
    @Value("${mongodb.secure}")
    private Boolean secure;
    
    @Value("${mongodb.admin}")
    private String dbAdmin;
 
    @Bean(name = "mongoTemplateDefault")
    public MongoTemplate mongoTemplate(@Qualifier("mongoFactoryDefault") MongoDbFactory mongoFactory) throws Exception {
        return new MongoTemplate(mongoFactory);
    }
 
    @Bean("mongoFactoryDefault")
    public MongoDbFactory mongoFactory(@Qualifier("mongoPropertiesDefault") MongoProperties mongoProperties) throws Exception {
    	
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
 
    @Bean(name = "mongoPropertiesDefault")
    @ConfigurationProperties(prefix = "mongodb.default")
    public MongoProperties properties() throws Exception {
        return new MongoProperties();
    }
 
    @Bean(name = "mongobeeDefault")
    public Mongobee mongobee(final @Qualifier("mongoPropertiesDefault") MongoProperties mongoProperties,
                             final @Qualifier("mongoTemplateDefault") MongoTemplate mongoTemplate,
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
                "com.arkeup.link_innov.gestion_profil_mcs.contrainte.mongo.changesets.def"); // the package to be scanned for changesets

        return runner;
    }
}
