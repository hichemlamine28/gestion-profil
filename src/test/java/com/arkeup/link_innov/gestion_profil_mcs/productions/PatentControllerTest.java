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
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
////@RunWith(SpringRunner.class)
////@WebAppConfiguration
////@SpringBootTest
////@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//public class PatentControllerTest {
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
//	public void PatentUnauthorizedAccess() throws Exception {
//		mockMvc.perform(post("/production/patent/create")).andExpect(status().isUnauthorized());
//
//		mockMvc.perform(put("/production/patent/update")).andExpect(status().isUnauthorized());
//
//		//mockMvc.perform(delete("/production/patent/delete")).andExpect(status().isUnauthorized());
//
//		mockMvc.perform(get("/production/patent/list")).andExpect(status().isUnauthorized());
//	}
//
//	//@Test
//	public void createPatent() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//
//		PatentDTO patentDTO = new PatentDTO();
//		patentDTO.setPublicationNumber(10);
//		patentDTO.setDescription("this should be the description");
//
//		String input = objectMapper.writeValueAsString(patentDTO);
//
//		MockHttpServletRequestBuilder requestBuilder = post("/production/patent/create").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		MvcResult resultPost = mockMvc.perform(requestBuilder).andReturn();
//		String resultString = resultPost.getResponse().getContentAsString();
//		PatentDTO result = objectMapper.readValue(resultString, PatentDTO.class);
//
//		Assert.assertEquals(result.isError(), false);
//
//		// test validation
//		patentDTO.setPublicationNumber(null);
//		input = objectMapper.writeValueAsString(patentDTO);
//
//		requestBuilder = post("/production/patent/create").accept(MediaType.APPLICATION_JSON)
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
//	public void updatePatent() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//
//		// add a patent
//		PatentDTO patentDTO = new PatentDTO();
//		patentDTO.setPublicationNumber(10);
//		patentDTO.setDescription("this is should be the description");
//
//		String input = objectMapper.writeValueAsString(patentDTO);
//
//		MockHttpServletRequestBuilder requestBuilder = post("/production/patent/create").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		MvcResult resultrequest = mockMvc.perform(requestBuilder).andReturn();
//		String resultString = resultrequest.getResponse().getContentAsString();
//		PatentDTO result = objectMapper.readValue(resultString, PatentDTO.class);
//
//		// update
//		result.setDescription("this should be the description for a new patent : Updated");
//		result.setUrl("http://test.com");
//		input = objectMapper.writeValueAsString(result);
//
//		requestBuilder = put("/production/patent/update").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//				.content(input);
//
//		resultrequest = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultrequest.getResponse().getContentAsString();
//		result = objectMapper.readValue(resultString, PatentDTO.class);
//
//		Assert.assertEquals(result.isError(), false);
//
//	}
//
//	//@Test
//	public void getPatent() throws Exception {
//
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//		MockHttpServletRequestBuilder requestBuilder;
//		MvcResult resultrequest;
//		String resultString;
//
//		// add patents
//		int count = 0;
//		List<PatentDTO> lstPatentDTO = new ArrayList<>(2);
//		lstPatentDTO.add(new PatentDTO());
//		lstPatentDTO.add(new PatentDTO());
//		for (PatentDTO patentDTO : lstPatentDTO) {
//			patentDTO.setDescription("this should be the description for a new patent " + ++count);
//			patentDTO.setPublicationNumber(10);
//
//			String input = objectMapper.writeValueAsString(patentDTO);
//
//			requestBuilder = post("/production/patent/create").accept(MediaType.APPLICATION_JSON)
//					.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON)
//					.content(input);
//
//			resultrequest = mockMvc.perform(requestBuilder).andReturn();
//			resultString = resultrequest.getResponse().getContentAsString();
//			patentDTO = objectMapper.readValue(resultString, PatentDTO.class);
//		}
//
//		requestBuilder = get("/production/patent/list").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON);
//
//		resultrequest = mockMvc.perform(requestBuilder).andReturn();
//		resultString = resultrequest.getResponse().getContentAsString();
//		ObjectNode node = objectMapper.readValue(resultString, ObjectNode.class);
//		String result = node.get("content").toString();
//		List<PatentDTO> lstResult = objectMapper.readValue(result, new TypeReference<List<PatentDTO>>() {
//		});
//
//		lstResult.forEach(patent -> {
//			Assert.assertEquals(patent.isError(), false);
//		});
//
//		// delete all patent
//		for (PatentDTO patent : lstResult) {
//			if (patent != null) {
//				requestBuilder = delete("/production/patent/" + patent.getId() + "/delete").accept(MediaType.APPLICATION_JSON)
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
