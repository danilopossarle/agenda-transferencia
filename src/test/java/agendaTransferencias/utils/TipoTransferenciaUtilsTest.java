package agendaTransferencias.utils;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Teste unitário para a classe utilitária {@link TipoTransferenciaUtils}
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
public class TipoTransferenciaUtilsTest {

    @Test
    public void getTipoTransferenciaChoices() {
        Map<TipoTransferencia, String> choices = TipoTransferenciaUtils.getTipoTransferenciaChoices();

        Assert.assertTrue(choices.containsKey(null));
        Assert.assertEquals(choices.get(null), "Selecione");

        for (TipoTransferencia tipo : TipoTransferencia.values()) {
            Assert.assertTrue(choices.containsKey(tipo));
            Assert.assertEquals(choices.get(tipo), tipo.toString());
        }
    }

}
