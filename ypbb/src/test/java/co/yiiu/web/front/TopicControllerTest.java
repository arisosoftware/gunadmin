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
public class TopicControllerTest {

	private final static String REMEMBERMECOOKIE = "Y2YwYTg0NDMtNmI4Ny00ZGU1LTg1ZTItMDI2YzYwNjY1N2Vk";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	public void create() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/topic/create").header("Cookie",
				"remember-me=" + TopicControllerTest.REMEMBERMECOOKIE)).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/topic/delete?id=10000000").header("Cookie",
				"remember-me=" + TopicControllerTest.REMEMBERMECOOKIE)).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void detail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/topic/1").header("Cookie",
				"remember-me=" + TopicControllerTest.REMEMBERMECOOKIE)).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Test
	public void edit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/topic/edit?id=1").header("Cookie",
				"remember-me=" + TopicControllerTest.REMEMBERMECOOKIE)).andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void tagTopics() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/topic/tag/字子?pageNo=1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(content().contentType(MediaType.TEXT_HTML))
				.andReturn();
	}
}