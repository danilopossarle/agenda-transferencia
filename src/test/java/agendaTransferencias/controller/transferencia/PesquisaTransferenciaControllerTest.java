package agendaTransferencias.controller.transferencia;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertAndReturnModelAttributeOfType;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;

import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.service.transferencia.TransferenciaService;
import agendaTransferencias.utils.model.PesquisaTransferenciaModel;

/**
 * Teste unitário para {@link PesquisaTransferenciaController}
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class PesquisaTransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @Mock
    private Transferencia transferencia1;

    @Mock
    private Transferencia transferencia2;

    @InjectMocks
    private PesquisaTransferenciaController pesquisaTransferenciaController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.pesquisaTransferenciaController).build();
    }

    @Test
    public void criaPaginaPesquisa() {
        ExtendedModelMap modelMap = new ExtendedModelMap();
        ModelAndView modelAndView = this.pesquisaTransferenciaController.criaPaginaPesquisa(modelMap);
        assertAndReturnModelAttributeOfType(modelAndView, "pesquisaModel", PesquisaTransferenciaModel.class);
        assertAndReturnModelAttributeOfType(modelAndView, "transferencias", LinkedList.class);
        assertAndReturnModelAttributeOfType(modelAndView, "tiposTransferencia", LinkedHashMap.class);
        assertViewName(modelAndView, "consultaTransferencias");
    }

    @Test
    public void realizarPesquisa() throws Exception {
        List<Transferencia> transferencias = Arrays.asList(this.transferencia1, this.transferencia2);
        when(this.transferenciaService.findTransferenciasBy(any(PesquisaTransferenciaModel.class))).thenReturn(transferencias);
        when(this.transferencia1.getId()).thenReturn(1L);
        when(this.transferencia2.getId()).thenReturn(2L);

        this.mockMvc.perform(post("/realizarPesquisaTransferencia"))
                .andExpect(status().isOk())
                .andExpect(view().name("consultaTransferencias")).andExpect(model().attribute("transferencias", hasSize(2)))
                .andExpect(model().attribute("transferencias", hasItem(allOf(hasProperty("id", is(1L))))))
                .andExpect(model().attribute("transferencias", hasItem(allOf(hasProperty("id", is(2L))))));
    }

    @Test
    public void excluirTransferencia() throws Exception {
        List<Transferencia> transferencias = Arrays.asList(this.transferencia1, this.transferencia2);
        when(this.transferenciaService.findAll()).thenReturn(transferencias);
        when(this.transferencia1.getId()).thenReturn(1L);
        when(this.transferencia2.getId()).thenReturn(2L);
        
        this.mockMvc.perform(get("/excluirTransferencia").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("consultaTransferencias"))
                .andExpect(model().attribute("transferencias", hasSize(2)))
                .andExpect(model().attribute("transferencias", hasItem(allOf(hasProperty("id", is(1L))))))
                .andExpect(model().attribute("transferencias", hasItem(allOf(hasProperty("id", is(2L))))));
    }
}
