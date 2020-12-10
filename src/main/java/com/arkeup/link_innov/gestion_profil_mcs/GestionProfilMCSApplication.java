package com.arkeup.link_innov.gestion_profil_mcs;

import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.dynamic_page.DynamicPageCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.user_auth.UserAuthCUDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.arkeup.link_innov.gestion_profil_mcs.commun.ssl.SSLUtilities;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.category.CategoryCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil.ProfilCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.activitysector.ActivitySectorCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.classification.ClassificationCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.numberofemployee.NumberOfEmployeeCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.thematicarea.ThematicAreaCUDSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.typology.TypologyCUDSA;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableFeignClients
@EnableAsync
@PropertySource("classpath:param_fonctionnel.properties")
@EnableNeo4jRepositories("com.arkeup.link_innov.gestion_profil_mcs.service.repository.neo4j")
public class GestionProfilMCSApplication implements ApplicationRunner {

	@Autowired
	private ProfilCUDSA profilCUDSA;

	@Autowired
	private CategoryCUDSA categoryCUDSA;

	@Autowired
	private ActivitySectorCUDSA activitySectorCUDSA;

	@Autowired
	private ClassificationCUDSA classificationCUDSA;

	@Autowired
	private NumberOfEmployeeCUDSA numberOfEmployeeCUDSA;

	@Autowired
	private ThematicAreaCUDSA thematicAreaCUDSA;

	@Autowired
	private TypologyCUDSA typologyCUDSA;

	@Autowired
	private DynamicPageCUDSA dynamicPageCUDSA;
	@Autowired
	private UserAuthCUDSA userAuthCUDSA;


	public static void main(String[] args) {

		SSLUtilities.trustAllHostnames();
		SSLUtilities.trustAllHttpsCertificates();
		SpringApplication.run(GestionProfilMCSApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		profilCUDSA.initProfiles();
		userAuthCUDSA.initUserTestPremiumAuth();
		categoryCUDSA.initDatabase();
		activitySectorCUDSA.initDatabase();
		classificationCUDSA.initDatabase();
		numberOfEmployeeCUDSA.initDatabase();
		thematicAreaCUDSA.initDatabase();
		typologyCUDSA.initDatabase();
		dynamicPageCUDSA.initDatabase();
	}
}
