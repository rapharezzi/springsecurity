package rezzi.springsecurity;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import rezzi.springsecurity.security.controller.AuthenticationController;

public class AuthenticationControllerTest extends SpringSecurityApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mock;

	@Autowired
	private AuthenticationController controller;

	private String auth = "{ \"username\": \"rapharezzi\", \"password\": \"123456\" }";

	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getTokenByAuthentication() throws Exception {
		MvcResult result = this.mock.perform(
				MockMvcRequestBuilders.post("/auth").content(auth).contentType(contentType))
				.andExpect(status().isOk()).andReturn();

		JSONObject objJson = new JSONObject(result.getResponse().getContentAsString());
		String token = objJson.getJSONObject("data").get("token").toString();
		assertEquals(true, (token != null && !token.isEmpty()));
	}
}
