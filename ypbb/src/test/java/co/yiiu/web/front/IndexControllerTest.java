package co.yiiu.web.front;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by tomoya at 2018/7/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	public void adminlogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/adminlogin")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void index() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/?tab=&p=1")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void logout() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void search() {
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void tags() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/tags?p=1")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void toLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void top100() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/top100")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void toRegister() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/register")).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}
}