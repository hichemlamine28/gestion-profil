///**
// * 
// */
//package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.hamcrest.core.IsNull;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.ldap.NameAlreadyBoundException;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.MailParametersDTOFactory;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RabbitMQUserDTOFactory;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RegistrationFactory;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilFactory;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial.ReseauSocialUserDTOFactory;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.user_auth.UserAuthMapper;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.MailParametersDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.RabbitMQUserDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.SignUpDTO;
//import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.NotificationMCS;
//import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.RabbitMQUsersMCS;
//import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxMCS;
//import com.arkeup.link_innov.gestion_profil_mcs.service.businessdelegate.ReseauxSociauxOAuthCredentialsMCS;
//import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.RegistrationCUDSM;
//import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProfilRepository;
//import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;
//import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.RegistrationMongoRepository;
//
///**
// * @author mikajy
// *
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//public class SignUpSAImplTest {
//	
//	@Value("${security.jwt.client-id}")
//	private String clientId;
//
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//	
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private SignUpSA signUpSA;
//
//	@MockBean
//	private UserAuthLdapRepository userAuthLdapRepository;
//
//	@MockBean
//	private ProfilRepository profilRepository;
//
//	@Autowired
//	private ProfilFactory profilFactory;
//
//	@Autowired
//	private UserAuthMapper userAuthMapper;
//
//	private SignUpDTO signUpExceptionDto;
//
//	@MockBean
//	private RabbitMQUsersMCS mockRabbitMQUsersMCS;
//	
//	@Autowired
//	private RabbitMQUserDTOFactory rabbitMQUserDTOFactory;
//
//	@Autowired
//	private RegistrationMongoRepository registrationMongoRepository;
//	
//	@MockBean
//	private RegistrationCUDSM registrationCUDSM;
//	
//	@Autowired
//	private RegistrationFactory registrationFactory;
//	
//	@MockBean
//	private NotificationMCS mockNotificationMCS;
//	
//	@MockBean
//	private ReseauxSociauxOAuthCredentialsMCS reseauxSociauxMCS;
//	
//	@Autowired
//	private ReseauSocialUserDTOFactory reseauSocialUserDTOFactory;
//	
//	@Autowired
//	private MailParametersDTOFactory mailParametersDTOFactory;
//	
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
////		jwtoken = obtainAccessToken();
//		CategoryDTO academicien = new CategoryDTO();
//		academicien.setId("azerty");
//		academicien.setName("Academicien");
//
//		CategoryDTO industriel = new CategoryDTO();
//		industriel.setId("uiop");
//		industriel.setName("Industriel");
//
//		CategoryDTO autres = new CategoryDTO();
//		industriel.setId("qsdf");
//		industriel.setName("autres acteur");
//
//		CorporationDTO employeur = new CorporationDTO();
//		employeur.setId("jklm");
//		employeur.setName("Université quelconque");
//
//		signUpExceptionDto = new SignUpDTO();
//		signUpExceptionDto.setUsername(RandomStringUtils.randomAlphabetic(10) + "@domain.com");
//		signUpExceptionDto.setFirstName("firstname");
//		signUpExceptionDto.setLastName("lastname");
//		signUpExceptionDto.setType(autres);
//		signUpExceptionDto.setEmployer(employeur);
//
//		Mockito.when(mockRabbitMQUsersMCS.deleteUser(signUpExceptionDto.getUsername()))
//				.thenReturn(new RabbitMQUserDTO());
//		
//		RabbitMQUserDTO rabbitMQUserDTO = rabbitMQUserDTOFactory.getInstance(signUpExceptionDto.getUsername());
//		Mockito.when(mockRabbitMQUsersMCS.createUser(rabbitMQUserDTO)).thenReturn(rabbitMQUserDTO);
//		
//		MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1,"fr","userTest","fc14226a-fd57-11e8-8eb2-f2801f1b9fd1","profile-uuid");
//		Mockito.when(mockNotificationMCS.sendEmail(mailParametersDTO)).thenReturn(mailParametersDTO);
//		
//		ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(signUpExceptionDto.getUuid(), signUpExceptionDto.getLastName(), signUpExceptionDto.getFirstName());
//		Mockito.when(reseauxSociauxMCS.createUser(reseauSocialUserDTO)).thenReturn(reseauSocialUserDTO);
//	}
//
//	@Test
//	public void testSignUpLdapExceptionRollback() {
//		UserAuth userAuth = userAuthMapper.userAuthDTOtoUserAuth(signUpExceptionDto);
//		Mockito.when(userAuthLdapRepository.save(userAuth)).thenThrow(NameAlreadyBoundException.class);
//
//		ProfilDTO profilDTO = null;
//		try {
//			profilDTO = signUpSA.doSignUp(signUpExceptionDto);
//		} catch (Exception e) {
//			assertThat(profilDTO, is(IsNull.nullValue()));
//
//			// ldap credentials should be deleted
//			UserAuth ldapUserAuth = userAuthLdapRepository.findByUsername(signUpExceptionDto.getUsername());
//			assertThat(ldapUserAuth, is(IsNull.nullValue(UserAuth.class)));
//
//			// Mongo profil should be deleted
//			Profil profil = profilRepository.getInformation(signUpExceptionDto.getUsername());
//			assertThat(profil, is(IsNull.nullValue(Profil.class)));
//
//			// Mongo registration should be deleted
//			Registration registration = registrationMongoRepository.findOneByUseruid(signUpExceptionDto.getUsername());
//			assertThat(registration, is(IsNull.nullValue(Registration.class)));
//		}
//
//	}
//
//	@Test
//	public void testSignUpProfilExceptionRollback() {
//		Profil profil = profilFactory.getEntityInstance(signUpExceptionDto);
//		Mockito.when(profilRepository.save(profil)).thenThrow(NullPointerException.class);
//		ProfilDTO profilDTO = null;
//		try {
//			profilDTO = signUpSA.doSignUp(signUpExceptionDto);
//		} catch (NullPointerException e) {
//			assertThat(profilDTO, is(IsNull.nullValue()));
//
//			// ldap credentials should be deleted
//			UserAuth ldapUserAuth = userAuthLdapRepository.findByUsername(signUpExceptionDto.getUsername());
//			assertThat(ldapUserAuth, is(IsNull.nullValue(UserAuth.class)));
//
//			// Mongo profil should be deleted
//			Profil nullProfil = profilRepository.getInformation(signUpExceptionDto.getUsername());
//			assertThat(nullProfil, is(IsNull.nullValue(Profil.class)));
//
//			// Mongo registration should be deleted
//			Registration registration = registrationMongoRepository.findOneByUseruid(signUpExceptionDto.getUsername());
//			assertThat(registration, is(IsNull.nullValue(Registration.class)));
//		}
//	}
//
//	// @Test
//	public void testSignUpRegistrationExceptionRollback() {
//		Registration registration = registrationFactory.getEntityInstance(signUpExceptionDto.getUsername());
//		Mockito.when(registrationCUDSM.save(registration)).thenThrow(NullPointerException.class);
//		ProfilDTO profilDTO = null;
//		try {
//			profilDTO = signUpSA.doSignUp(signUpExceptionDto);
//		} catch (NullPointerException e) {
//			assertThat(profilDTO, is(IsNull.nullValue()));
//
//			// ldap credentials should be deleted
//			UserAuth ldapUserAuth = userAuthLdapRepository.findByUsername(signUpExceptionDto.getUsername());
//			assertThat(ldapUserAuth, is(IsNull.nullValue(UserAuth.class)));
//
//			// Mongo profil should be deleted
//			Profil nullProfil = profilRepository.getInformation(signUpExceptionDto.getUsername());
//			assertThat(nullProfil, is(IsNull.nullValue(Profil.class)));
//
//			// Mongo registration should be deleted
//			Registration nullRegistration = registrationMongoRepository.findOneByUseruid(signUpExceptionDto.getUsername());
//			assertThat(nullRegistration, is(IsNull.nullValue(Registration.class)));
//		}
//	}
//
//	
//	private String obtainAccessToken() throws Exception {
//
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "client_credentials");
//		params.add("client_id", clientId);
//		params.add("client_secret", clientSecret);
//
//		ResultActions result = mockMvc
//				.perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
//						.accept("application/json;charset=UTF-8"))
//				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
//
//		String resultString = result.andReturn().getResponse().getContentAsString();
//
//		JacksonJsonParser jsonParser = new JacksonJsonParser();
//		return jsonParser.parseMap(resultString).get("access_token").toString();
//	}
//}
