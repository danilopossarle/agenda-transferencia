package agendaTransferencias.utils.calculadora;

import static org.joda.time.Days.daysBetween;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.time.DateTime;

/**
 * Classe utilitária para as calculadoras de taxa.
 * 
 * @author danilo.possarle
 * @created Dec 16, 2015
 */
public class CalculadoraTaxaUtils {

    /**
     * Formata a taxa com 2 casas decimais.
     * 
     * @param taxa a taxa a ser formatada
     */
    public static BigDecimal arredondaTaxa(BigDecimal taxa) {
        return taxa.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Calcula a quantidade de dias existentes em um período
     * 
     * @param dataInicial data inicial do período
     * @param dataFinal data final do período
     * @return quantidade de dias existentes em um período
     */
    public static int calculaQuantidadeDias(DateTime dataInicial, DateTime dataFinal) {
        return daysBetween(dataInicial, dataFinal).getDays();
    }

}
