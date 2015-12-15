package agendaTransferencias.utils.factory;

import static agendaTransferencias.utils.TipoTransferencia.A;
import static agendaTransferencias.utils.TipoTransferencia.B;
import static agendaTransferencias.utils.TipoTransferencia.C;
import static agendaTransferencias.utils.TipoTransferencia.D;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import agendaTransferencias.utils.TipoTransferencia;
import agendaTransferencias.utils.calculadora.CalculadoraTaxa;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaA;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaB;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaC;
import agendaTransferencias.utils.calculadora.CalculadoraTaxaD;

/**
 * Fábrica de calculadoras para os diferentes tipos de transferências aceitos na aplicação. Essa fábrica será um bean do spring que poderá
 * ser injetado onde for necessário.
 * 
 * @author danilo.possarle
 * @created Dec 10, 2015
 */
@Component
public class CalculadoraTaxaFactory {

    private final Map<TipoTransferencia, CalculadoraTaxa> calculadorasMap = new HashMap<TipoTransferencia, CalculadoraTaxa>();

    /**
     * Construtor. Quando invocado para a criação do bean, realizará a injeção das dependencias que essa factory possui.
     * 
     * @param calculadoraTaxaA {@link CalculadoraTaxaA}
     * @param calculadoraTaxaB {@link CalculadoraTaxaB}
     * @param calculadoraTaxaC {@link CalculadoraTaxaC}
     * @param calculadoraTaxaD {@link CalculadoraTaxaD}
     */
    @Autowired
    public CalculadoraTaxaFactory(CalculadoraTaxaA calculadoraTaxaA, CalculadoraTaxaB calculadoraTaxaB, CalculadoraTaxaC calculadoraTaxaC,
            CalculadoraTaxaD calculadoraTaxaD) {
        this.calculadorasMap.put(A, calculadoraTaxaA);
        this.calculadorasMap.put(B, calculadoraTaxaB);
        this.calculadorasMap.put(C, calculadoraTaxaC);
        this.calculadorasMap.put(D, calculadoraTaxaD);
    }

    /**
     * Procura por uma {@link CalculadoraTaxa} de um {@link TipoTransferencia}
     * 
     * @param tipoTransferencia {@link TipoTransferencia}
     * @return {@link CalculadoraTaxa} para o {@link TipoTransferencia} informado ou nulo caso não encontre nenhuma calculadora
     */
    public CalculadoraTaxa calculadoraPara(TipoTransferencia tipoTransferencia) {
        if (tipoTransferencia == null) {
            return null;
        }
        return this.calculadorasMap.get(tipoTransferencia);
    }
}
