//package com.arkeup.link_innov.gestion_profil_mcs.authentification;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.json.JacksonJsonParser;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
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
//import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserInformationDTO;
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
//public class LoginControllerTest {
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
//	/**
//	 * @throws Exception
//	 *
//	 */
//	//@Test
//	public void login() throws Exception {
//		MockHttpServletResponse response = this.obtainAccessToken("userTest", "userTestPassword");
//		UserInformationDTO result = new UserInformationDTO();
//
//		if(response.getStatus() == 200) {
//			String accessToken = new JacksonJsonParser().parseMap(response.getContentAsString()).get("access_token").toString();
//
//			MockHttpServletRequestBuilder requestBuilder = get("/authentification/login").accept(MediaType.APPLICATION_JSON)
//					.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON);
//
//			MvcResult resultPost = mockMvc.perform(requestBuilder).andReturn();
//			String resultString = resultPost.getResponse().getContentAsString();
//			result = objectMapper.readValue(resultString, UserInformationDTO.class);
//
//			Assert.assertEquals(false, result.isError());
//		} else {
//			Assert.fail("username and password are incorrect");
//		}
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
//	private MockHttpServletResponse obtainAccessToken(String username, String password) throws Exception {
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
//		return result.andReturn().getResponse();
//	}
//}
