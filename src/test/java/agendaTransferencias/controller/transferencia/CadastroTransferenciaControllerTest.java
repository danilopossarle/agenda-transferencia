package agendaTransferencias.controller.transferencia;

import static java.math.BigDecimal.ONE;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.ModelAndViewAssert.assertAndReturnModelAttributeOfType;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;

import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.service.transferencia.TransferenciaService;
import agendaTransferencias.utils.validator.TransferenciaValidator;

/**
 * Teste unitário para {@link CadastroTransferenciaController}
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CadastroTransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @Mock
    private TransferenciaValidator transferenciaValidator;

    @InjectMocks
    private CadastroTransferenciaController cadastroTransferenciaController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.cadastroTransferenciaController).build();
    }

    @Test
    public void cadastroTransferencia() {
        ExtendedModelMap modelMap = new ExtendedModelMap();
        ModelAndView modelAndView = this.cadastroTransferenciaController.cadastroTransferencia(modelMap);
        assertAndReturnModelAttributeOfType(modelAndView, "transferencia", Transferencia.class);
        assertAndReturnModelAttributeOfType(modelAndView, "tiposTransferencia", LinkedHashMap.class);
        assertViewName(modelAndView, "cadastroTransferencia");
    }

    @Test
    public void realizarCadastroTransferencia() throws Exception {
        when(this.transferenciaValidator.supports(Transferencia.class)).thenReturn(true);
        this.mockMvc.perform(post("/realizarCadastroTransferencia").param("contaOrigem", "65432-1").param("contaDestino", "12345-6")
                        .param("dataTransferencia", "16/12/2015").param("taxa", "10").param("valor", "10000").param("tipo", "B"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("cadastroTransferencia"))
                        .andExpect(model().attribute("msgSuccess", "Cadastro realizado com sucesso"));
    }
    
    @Test
    public void calculaTaxa() throws Exception {
        when(this.transferenciaValidator.supports(Transferencia.class)).thenReturn(true);
        when(this.transferenciaService.calculaTaxa(any(Transferencia.class))).thenReturn(ONE);
        MvcResult result = this.mockMvc.perform(post("/calcularTaxa").param("contaOrigem", "65432-1").param("contaDestino", "12345-6")
                        .param("dataTransferencia", "16/12/2015").param("taxa", "10").param("valor", "10000").param("tipo", "B"))
                        .andExpect(status().isOk()).andReturn();
        
        BigDecimal taxa = new BigDecimal(result.getResponse().getContentAsString());
        
        Assert.assertEquals(ONE, taxa);
    }
}
