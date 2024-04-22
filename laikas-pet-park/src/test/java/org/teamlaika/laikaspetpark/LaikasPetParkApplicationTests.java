import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void loginWithValidCredentials() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
						.param("username", "testuser")
						.param("password", "password")
						.param("accountType", "Owner"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Login successful"));
	}

	@Test
	void loginWithInvalidCredentials() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
						.param("username", "invaliduser")
						.param("password", "invalidpassword")
						.param("accountType", "Owner"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string("Invalid username or password"));
	}
}
