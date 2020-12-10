//package com.arkeup.link_innov.gestion_profil_mcs.productions;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Assert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.http.MediaType;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
////@RunWith(SpringRunner.class)
////@WebAppConfiguration
////@SpringBootTest
////@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//public class OtherProductionControllerTest {
//
//	@Autowired
//	private WebApplicationContext wac;
//
//	@Autowired
//	private FilterChainProxy springSecurityFilterChain;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	private MockMvc mockMvc;
//
//	@Value("${security.jwt.client-id}")
//	private String clientId;
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//
//	//@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
//	}
//
//	//@Test
//	public void OtherProductionUnauthorizedAccess() throws Exception {
//		mockMvc.perform(post("/production/other/create")).andExpect(status().isUnauthorized());
//
//		mockMvc.perform(put("/production/other/update")).andExpect(status().isUnauthorized());
//
//		//mockMvc.perform(delete("/production/other/delete")).andExpect(status().isUnauthorized());
//
//		mockMvc.perform(get("/production/other/list")).andExpect(status().isUnauthorized());
//	}
//
//	//@Test
//	public void createOtherProduction() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//
//		OtherProductionDTO otherProductionDTO = new OtherProductionDTO();
//		otherProductionDTO.setPostType("postType");
//		otherProductionDTO.setDescription("this should be the description");
//
//		String input = objectMapper.writeValueAsString(otherProductionDTO);
//
//		MockHttpServletRequestBuilder requestBuilder = post("/production/other/create").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		MvcResult resultPost = mockMvc.perform(requestBuilder).andReturn();
//		String resultString = resultPost.getResponse().getContentAsString();
//		OtherProductionDTO result = objectMapper.readValue(resultString, OtherProductionDTO.class);
//
//		Assert.assertEquals(result.isError(), false);
//
//		// test validation
//		otherProductionDTO.setLink("invalid url");
//		input = objectMapper.writeValueAsString(otherProductionDTO);
//
//		requestBuilder = post("/production/other/create").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		resultPost = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultPost.getResponse().getContentAsString();
//		Assert.assertEquals(resultString.contains("\"isError\":true"), true);
//
//	}
//
//	//@Test
//	public void updateOtherProduction() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//
//		// add a otherProduction
//		OtherProductionDTO otherProductionDTO = new OtherProductionDTO();
//		otherProductionDTO.setPostType("postType");
//		otherProductionDTO.setDescription("this is should be the description");
//
//		String input = objectMapper.writeValueAsString(otherProductionDTO);
//
//		MockHttpServletRequestBuilder requestBuilder = post("/production/other/create").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		MvcResult resultrequest = mockMvc.perform(requestBuilder).andReturn();
//		String resultString = resultrequest.getResponse().getContentAsString();
//		OtherProductionDTO result = objectMapper.readValue(resultString, OtherProductionDTO.class);
//
//		// update
//		result.setDescription("this should be the description for a new otherProduction : Updated");
//		result.setUrl("http://test.com");
//		input = objectMapper.writeValueAsString(result);
//
//		requestBuilder = put("/production/other/update").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		resultrequest = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultrequest.getResponse().getContentAsString();
//		result = objectMapper.readValue(resultString, OtherProductionDTO.class);
//
//		Assert.assertEquals(result.isError(), false);
//
//	}
//
//	//@Test
//	public void getOtherProduction() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//		MockHttpServletRequestBuilder requestBuilder;
//		MvcResult resultrequest;
//		String resultString;
//
//		// add otherProductions
//		int count = 0;
//		List<OtherProductionDTO> lstOtherProductionDTO = new ArrayList<>(2);
//		lstOtherProductionDTO.add(new OtherProductionDTO());
//		lstOtherProductionDTO.add(new OtherProductionDTO());
//		for (OtherProductionDTO otherProductionDTO : lstOtherProductionDTO) {
//			otherProductionDTO.setDescription("this should be the description for a new otherProduction " + ++count);
//			otherProductionDTO.setPostType("postType");
//
//			String input = objectMapper.writeValueAsString(otherProductionDTO);
//
//			requestBuilder = post("/other/create").accept(MediaType.APPLICATION_JSON)
//					.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//					.content(input);
//
//			resultrequest = mockMvc.perform(requestBuilder).andReturn();
//			resultString = resultrequest.getResponse().getContentAsString();
//			otherProductionDTO = objectMapper.readValue(resultString, OtherProductionDTO.class);
//		}
//
//		requestBuilder = get("/production/other/list").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON);
//
//		resultrequest = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultrequest.getResponse().getContentAsString();
//		ObjectNode node = objectMapper.readValue(resultString, ObjectNode.class);
//		String result = node.get("content").toString();
//		List<OtherProductionDTO> lstResult = objectMapper.readValue(result,
//				new TypeReference<List<OtherProductionDTO>>() {
//				});
//
//		lstResult.forEach(otherProduction -> {
//			Assert.assertEquals(otherProduction.isError(), false);
//		});
//
//		// delete all otherProduction
//		for (OtherProductionDTO otherProduction : lstResult) {
//			if (otherProduction != null) {
//				requestBuilder = delete("/production/other/" + otherProduction.getId() + "/delete").accept(MediaType.APPLICATION_JSON)
//						.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON);
//
//				resultrequest = mockMvc.perform(requestBuilder).andReturn();
//				resultString = resultrequest.getResponse().getContentAsString();
//			}
//		}
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
//}
