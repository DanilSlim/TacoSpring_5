package web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=tacos.SpringTaco5Application.class)
@WebMvcTest(web.HomeController.class)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mocMvc;
	
	@Test
	public void testHomePage() throws Exception {
		
		
		mocMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
		.andExpect(content().string(containsString("Welcome to...")));
		
	}

}
