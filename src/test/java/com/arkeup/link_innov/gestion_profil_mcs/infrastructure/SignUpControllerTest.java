///**
// *
// */
//package com.arkeup.link_innov.gestion_profil_mcs.infrastructure;
//
//
//import static org.hamcrest.CoreMatchers.both;
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Date;
//import java.util.Optional;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.hamcrest.core.IsNull;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.UserAlreadyExistException;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.MailParametersDTOFactory;
//import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RabbitMQUserDTOFactory;
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
//import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.user_auth.UserAuthCUDSM;
//import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProfilRepository;
//import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;
//import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.RegistrationMongoRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * @author mikajy
// *
// */
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//public class SignUpControllerTest {
//	
//	@Value("${security.jwt.client-id}")
//	private String clientId;
//
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//	
//    @Autowired
//    private UserAuthLdapRepository userAuthLdapRepository;
//    
//    @Autowired
//    private ProfilRepository profilRepository;
//    
//    @Autowired
//    private RegistrationMongoRepository registrationMongoRepository;
//
//	@Autowired
//    private WebApplicationContext cxt;
//
//	@Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//	@Autowired
//    private ObjectMapper objectMapper;
//	
//	@Autowired
//	private UserAuthMapper userAuthMapper;
//	
//	@MockBean
//	private RabbitMQUsersMCS mockRabbitMQUsersMCS;
//	
//	@MockBean
//	private NotificationMCS notificationMCS;
//	
//	@MockBean
//	private ReseauxSociauxOAuthCredentialsMCS reseauxSociauxMCS;
//	
//	@Autowired
//	private ReseauSocialUserDTOFactory reseauSocialUserDTOFactory;
//	
//	@Autowired
//	private RabbitMQUserDTOFactory rabbitMQUserDTOFactory;
//	
//	@Autowired
//	private MailParametersDTOFactory mailParametersDTOFactory;
//
//	private MockMvc mockMvc;
//
//	private SignUpDTO validDto;
//	private SignUpDTO inValidEmailAndLastnameDto;
//	private SignUpDTO signUpExceptionDto;
//	
//	private String jwtoken;
//	
//	@Test
//	public void testSuccessfullSignUp() throws Exception {
//		RabbitMQUserDTO rabbitMQUserDTO = rabbitMQUserDTOFactory.getInstance(validDto.getUsername());
//		Mockito.when(mockRabbitMQUsersMCS.createUser(rabbitMQUserDTO)).thenReturn(rabbitMQUserDTO);
//		
//		ReseauSocialUserDTO reseauSocialUserDTO = reseauSocialUserDTOFactory.getInstance(validDto.getUuid(), validDto.getLastName(), validDto.getFirstName());
//		Mockito.when(reseauxSociauxMCS.createUser(reseauSocialUserDTO)).thenReturn(reseauSocialUserDTO);
//		
//		MailParametersDTO mailParametersDTO = mailParametersDTOFactory.getInstance(1,"fr","userTest","fc14226a-fd57-11e8-8eb2-f2801f1b9fd1","profile-uuid");
//		Mockito.when(notificationMCS.sendEmail(mailParametersDTO)).thenReturn(mailParametersDTO);
//		
////		OAuth2ClientContext mockClient = Mockito.mock(OAuth2ClientContext.class);
////		Mockito.when(mockClient.getAccessToken()).thenReturn(new DefaultOAuth2AccessToken("my-fun-token"));
//        //String accessToken = obtainAccessToken();
//		
//        String validJson = objectMapper.writeValueAsString(validDto);
//    	MockHttpServletRequestBuilder requestBuilder = post("/signup").accept(MediaType.APPLICATION_JSON)
//    						.header("Authorization", "Bearer " + jwtoken)
//							.contentType(MediaType.APPLICATION_JSON)
//							.content(validJson);
//    	String response = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//    	ProfilDTO profilDTO = objectMapper.readValue(response, ProfilDTO.class);
//    	assertThat(profilDTO.getErrorCode(), is(IsNull.nullValue()));
//    	
//    	// ldap insertion should be ok
//    	UserAuth ldapUserAuth = userAuthLdapRepository.findByUsername(profilDTO.getUsername());
//    	assertThat(ldapUserAuth, is(IsNull.notNullValue(UserAuth.class)));
//    	assertThat(ldapUserAuth.getUsername(), is(equalTo(profilDTO.getUsername())));
//
//    	//Mongo profil should be ok
//    	Optional<Profil> optionnalProfil = profilRepository.findById(profilDTO.getId());
//    	assertThat(optionnalProfil.isPresent(), is(equalTo(Boolean.TRUE)));
//    	Profil profilEntity = optionnalProfil.get();
//    	assertThat(profilEntity, is(IsNull.notNullValue(Profil.class)));
//    	assertThat(profilEntity.getEmail(), is(equalTo(validDto.getMail())));
//    	
//    	// Mongo registration should be ok
//    	Registration registration = registrationMongoRepository.findOneByUseruid(profilEntity.getUsername());
//    	assertThat(registration, is(IsNull.notNullValue(Registration.class)));
//    	assertThat(registration.getId(), is(IsNull.notNullValue(String.class)));
//    	assertThat(registration.getCreationDate(), is(IsNull.notNullValue(Date.class)));
//    	assertThat(registration.getUseruid(), is(equalTo(profilEntity.getUsername())));
//    	
//    	// RabbitMQ credential should be created
////    	RabbitMQUserDTO rabbitMQUserDTO = rabbitMQUsersMCS.getCredentials(jwtoken, validDto.getUsername());
////    	assertThat(rabbitMQUserDTO, is(IsNull.notNullValue(RabbitMQUserDTO.class)));
////    	assertThat(rabbitMQUserDTO.getUserId(), is(equalTo(validDto.getUsername())));
//    	
//        
//    	
//    	
//	}
//
//	@Test
//	public void testSignUpValidationError() throws Exception {
//		String invalidData = objectMapper.writeValueAsString(inValidEmailAndLastnameDto);
//
//    	MockHttpServletRequestBuilder requestBuilder = post("/signup").accept(MediaType.APPLICATION_JSON)
//							.header("Authorization", "Bearer " + jwtoken)
//							.contentType(MediaType.APPLICATION_JSON)
//							.content(invalidData);
//    	 MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is5xxServerError()).andReturn();
//    	 String responseContent = result.getResponse().getContentAsString();
//
//    	 assertThat(responseContent, both(containsString("signup.mail.invalid")).and(containsString("signup.lastname.empty")));
//	}
//	
//	@Test
//	public void testSignUpLdapExceptionRollback() {
//		UserAuth ldapUser = userAuthMapper.userAuthDTOtoUserAuth(signUpExceptionDto);
//		UserAuthCUDSM mockUserAuthCUDSM = Mockito.mock(UserAuthCUDSM.class);
//		Mockito.when(mockUserAuthCUDSM.create(ldapUser)).thenThrow(UserAlreadyExistException.class);
//		
//	}
//	
//	@Before
//    public void setUp() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(cxt).addFilter(springSecurityFilterChain).build();
//		jwtoken = obtainAccessToken();
//        CategoryDTO academicien = new CategoryDTO();
//        academicien.setId("azerty");
//        academicien.setName("Academicien");
//
//        CategoryDTO industriel = new CategoryDTO();
//        industriel.setId("uiop");
//        industriel.setName("Industriel");
//
//        CategoryDTO autres = new CategoryDTO();
//        industriel.setId("qsdf");
//        industriel.setName("autres acteur");
//
//        CorporationDTO employeur = new CorporationDTO();
//        employeur.setId("jklm");
//        employeur.setName("Université quelconque");
//        String random = RandomStringUtils.randomAlphabetic(5);
//        validDto = new SignUpDTO();
//        validDto.setMail(random + "@domain.com");
//        validDto.setFirstName(random);
//        validDto.setLastName(random);
//        validDto.setType(academicien);
//        validDto.setEmployer(employeur);
//
//        inValidEmailAndLastnameDto = new SignUpDTO();
//        inValidEmailAndLastnameDto.setMail("invalid@test@domain.com");
//        inValidEmailAndLastnameDto.setFirstName("firstname");
//        inValidEmailAndLastnameDto.setLastName(" ");
//        inValidEmailAndLastnameDto.setType(industriel);
//        inValidEmailAndLastnameDto.setEmployer(employeur);
//
//        signUpExceptionDto = new SignUpDTO();
//        signUpExceptionDto.setMail(RandomStringUtils.randomAlphabetic(10) + "@domain.com");
//        signUpExceptionDto.setFirstName("firstname");
//        signUpExceptionDto.setLastName("lastname");
//        signUpExceptionDto.setType(autres);
//        signUpExceptionDto.setEmployer(employeur);
//        
//
//        /*when(signUpSA.doSignUp(validDto)).then(new Answer<ResponseDataDTO<SignUpDTO>>() {
//			@Override
//			public ResponseDataDTO<SignUpDTO> answer(InvocationOnMock invocation) throws Throwable {
//				SignUpDTO dto = invocation.getArgument(0);
//				dto.setId("newMockId");
//				return new ResponseDataDTO<SignUpDTO>(dto);
//			}
//		});*/
//    }
//	
//	private String obtainAccessToken() throws Exception {
//		
//		
//
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "client_credentials");
////		params.add("client_id", clientId);
////		params.add("client_secret", clientSecret);
//		
//		// "userTest", "userTestPassword"
////		params.add("grant_type", "password");
////		params.add("client_id", clientId);
////		params.add("username", "userTest");
////		params.add("password", "userTestPassword");
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
//	
//	
//
//}
