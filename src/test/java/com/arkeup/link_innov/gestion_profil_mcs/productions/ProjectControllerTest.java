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
//import java.util.Date;
//
//import org.elasticsearch.rest.RestRequest.Method;
//import org.junit.Assert;
//import org.junit.Before;
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
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProjectDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * @author Stéphan R.
// *
// */
////@RunWith(SpringRunner.class)
////@WebAppConfiguration
////@SpringBootTest
////@TestPropertySource(locations = "classpath:application-integrationtest.properties")
//public class ProjectControllerTest {
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
//
//	@Value("${security.jwt.client-secret}")
//	private String clientSecret;
//
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
//	}
//
//	//@Test
//	public void ProjectUnauthorizedAccess() throws Exception {
//		mockMvc.perform(post("/production/project/create")).andExpect(status().isUnauthorized());
//
//		mockMvc.perform(put("/production/project/update")).andExpect(status().isUnauthorized());
//
//		//mockMvc.perform(delete("/production/patent/delete")).andExpect(status().isUnauthorized());
//
//		mockMvc.perform(get("/production/project/list")).andExpect(status().isUnauthorized());
//	}
//
//	/**
//	 * @throws Exception
//	 */
//	//@Test
//	public void createProject() throws Exception {
//		ProjectDTO projectDTO = new ProjectDTO();
//		projectDTO.setDate(new Date());
//		projectDTO.setTitle("lorem ipsum dolores si amet");
//		projectDTO.setDescription("this should be the description");
//
//		String input = objectMapper.writeValueAsString(projectDTO);
//		projectDTO = objectMapper.readValue(this.request(Method.POST, "/production/project/create", MediaType.APPLICATION_JSON, input), ProjectDTO.class);
//
//		Assert.assertEquals(false, projectDTO.isError());
//	}
//
//	/**
//	 * @throws Exception
//	 */
//	//@Test
//	public void updateProject() throws Exception {
//		// Create project
//		ProjectDTO projectDTO = new ProjectDTO();
//		projectDTO.setDate(new Date());
//		projectDTO.setTitle("lorem ipsum dolores si amet");
//		projectDTO.setDescription("this should be the description");
//
//		String input = objectMapper.writeValueAsString(projectDTO);
//		projectDTO = objectMapper.readValue(this.request(Method.POST, "/production/project/create", MediaType.APPLICATION_JSON, input), ProjectDTO.class);
//
//		Assert.assertEquals(false, projectDTO.isError());
//
//		// Update it
//		projectDTO.setDescription("this should be the description for a new project : Updated");
//		projectDTO.setUrl("http://test.com");
//		input = objectMapper.writeValueAsString(projectDTO);
//
//		projectDTO = objectMapper.readValue(this.request(Method.PUT, "/production/project/update", MediaType.APPLICATION_JSON, input), ProjectDTO.class);
//
//		Assert.assertEquals(false, projectDTO.isError());
//	}
//
//	//@Test
//	public void deleteProject() throws Exception {
//		// Create project
//		ProjectDTO projectDTO = new ProjectDTO();
//		projectDTO.setDate(new Date());
//		projectDTO.setTitle("lorem ipsum dolores si amet");
//		projectDTO.setDescription("this should be the description");
//
//		String input = objectMapper.writeValueAsString(projectDTO);
//		projectDTO = objectMapper.readValue(this.request(Method.POST, "/production/project/create", MediaType.APPLICATION_JSON, input), ProjectDTO.class);
//
//		Assert.assertEquals(false, projectDTO.isError());
//
//		// Delete it
//		projectDTO = objectMapper.readValue(this.request(Method.DELETE , "/production/project/" + projectDTO.getId() + "/delete", MediaType.APPLICATION_JSON), ProjectDTO.class);
//
//		Assert.assertEquals(false, projectDTO.isError());
//	}
//
//	/**
//	 * Requesting ws
//	 *
//	 * @param url
//	 * @param mediaType
//	 * @param input
//	 *
//	 * @return
//	 *
//	 * @throws Exception
//	 */
//	private String request(Method method, String url, MediaType mediaType, String... input) throws Exception {
//		String accessToken = obtainAccessToken("userTest", "userTestPassword");
//		MockHttpServletRequestBuilder requestBuilder = null;
//
//		if(method.toString().equalsIgnoreCase("POST")) {
//			requestBuilder = post(url);
//		} else if(method.toString().equalsIgnoreCase("PUT")) {
//			requestBuilder = put(url);
//		} else {
//			requestBuilder = delete(url);
//		}
//
//		requestBuilder.accept(mediaType).header("Authorization", "Bearer " + accessToken).contentType(mediaType);
//
//		if(input.length > 0) {
//			requestBuilder.content(input[0]);
//		}
//
//		MvcResult resultPost = mockMvc.perform(requestBuilder).andReturn();
//
//		return resultPost.getResponse().getContentAsString();
//	}
//
//	/**
//	 * Get token from {{url}}/oauth/token
//	 *
//	 * @param username login
//	 * @param password password
//	 *
//	 * @return the token
//	 *
//	 * @throws Exception
//	 */
//	private String obtainAccessToken(String username, String password) throws Exception {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "password");
//		params.add("client_id", clientId);
//		params.add("username", username);
//		params.add("password", password);
//
//		ResultActions result = mockMvc.perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
//						.accept("application/json;charset=UTF-8"))
//				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
//
//		String resultString = result.andReturn().getResponse().getContentAsString();
//
//		return new JacksonJsonParser().parseMap(resultString).get("access_token").toString();
//	}
//}
