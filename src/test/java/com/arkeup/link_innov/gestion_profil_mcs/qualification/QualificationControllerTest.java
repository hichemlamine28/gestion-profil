//package com.arkeup.link_innov.gestion_profil_mcs.qualification;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Calendar;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.boot.test.context.SpringBootTest;
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
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//public class QualificationControllerTest {
//	@Autowired
//    private WebApplicationContext wac;
//	
//	@Autowired
//    private FilterChainProxy springSecurityFilterChain;
//	
//	@Autowired
//    private ObjectMapper objectMapper;
//	
//	private MockMvc mockMvc;
//	
//	@Value("${security.jwt.client-id}")
//	private String clientId;
//
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
//	}
//	
//	private String obtainAccessToken(String username, String password) throws Exception {
//
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "password");
//		params.add("client_id", clientId);
//		params.add("username", username);
//		params.add("password", password);
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
//	@Test
//	public void createQualification() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest@yopmail.com", "userTestPassword");
//
//		QualificationDTO qualificationDTO = new QualificationDTO();
//		
//		String input = objectMapper.writeValueAsString(qualificationDTO);
//
//		MockHttpServletRequestBuilder requestBuilder = post("/qualification/addQualification").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		MvcResult resultPost = mockMvc.perform(requestBuilder).andReturn();
//		String resultString = resultPost.getResponse().getContentAsString();
//		QualificationDTO result = objectMapper.readValue(resultString, QualificationDTO.class);
//
//		Assert.assertEquals(result.isError(), true);
//		
//		qualificationDTO.setInstitution("ASJA");
//		qualificationDTO.setDegree("License");
//		qualificationDTO.setField("Electronics");
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 1988);
//		cal.set(Calendar.MONTH, Calendar.JANUARY);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		qualificationDTO.setStartDate(cal.getTime());
//
//		input = objectMapper.writeValueAsString(qualificationDTO);
//
//		requestBuilder = post("/qualification/addQualification").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		resultPost = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultPost.getResponse().getContentAsString();
//		result = objectMapper.readValue(resultString, QualificationDTO.class);
//
//		Assert.assertEquals(result.isError(), false);
//
//	}
//	
//	// @Test
//	public void updateQualification() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest@yopmail.com", "userTestPassword");
//
//		QualificationDTO qualificationDTO = new QualificationDTO();
//		
//		qualificationDTO.setInstitution("ASJA");
//		qualificationDTO.setDegree("License");
//		qualificationDTO.setField("Electronics");
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 1988);
//		cal.set(Calendar.MONTH, Calendar.JANUARY);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		qualificationDTO.setStartDate(cal.getTime());
//		
//		String input = objectMapper.writeValueAsString(qualificationDTO);
//
//		MockHttpServletRequestBuilder requestBuilder = post("/qualification/addQualification").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		MvcResult resultPost = mockMvc.perform(requestBuilder).andReturn();
//		String resultString = resultPost.getResponse().getContentAsString();
//		QualificationDTO result = objectMapper.readValue(resultString, QualificationDTO.class);
//
//		// update
//		result.setField("Informatics");
//                
//		requestBuilder = put("/qualification/updateQualification").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		resultPost = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultPost.getResponse().getContentAsString();
//		result = objectMapper.readValue(resultString, QualificationDTO.class);
//
//		Assert.assertEquals(result.isError(), false);
//	}
//	
//	
//}
