package agendaTransferencias.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Teste unitário para a classe {@link HomeController}
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
public class HomeControllerTest {

	private final HomeController controller = new HomeController();
	
	private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }
    
    @Test
    public void home() throws Exception {
    	this.mockMvc.perform(post("/"))
    				.andExpect(status().isOk())
    				.andExpect(view().name("home"));
    }
	
}
