package agendaTransferencias.utils;

import static org.joda.time.Days.daysBetween;

import java.math.BigDecimal;

import agendaTransferencias.model.domain.Transferencia;

/**
 * Enum que representa os tipos de transferências aceitos na aplicação
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
public enum TipoTransferencia implements CalculoTaxaStrategy {

    A("A") {
        @Override
        public BigDecimal calcularTaxa(Transferencia transferencia) {
            BigDecimal percentualCobrado = new BigDecimal("0.03");
            return new BigDecimal("2").add(percentualCobrado.multiply(transferencia.getValor()));
        }
    },
    B("B") {
        @Override
        public BigDecimal calcularTaxa(Transferencia transferencia) {
            int intervaloEmDias = daysBetween(transferencia.getDataCadastro(), transferencia.getDataTransferencia()).getDays();
            if (intervaloEmDias <= 30) {
                return BigDecimal.TEN;
            }
            return new BigDecimal("8");
        }
    },
    C("C") {
        @Override
        public BigDecimal calcularTaxa(Transferencia transferencia) {
            int intervaloEmDias = daysBetween(transferencia.getDataCadastro(), transferencia.getDataTransferencia()).getDays();
            if (intervaloEmDias <= 5) {
                return new BigDecimal("8.3");
            }
            if (intervaloEmDias <= 10) {
                return new BigDecimal("7.4");
            }
            if (intervaloEmDias <= 15) {
                return new BigDecimal("6.7");
            }
            if (intervaloEmDias <= 20) {
                return new BigDecimal("5.4");
            }
            if (intervaloEmDias <= 25) {
                return new BigDecimal("4.3");
            }
            if (intervaloEmDias <= 30) {
                return new BigDecimal("2.1");
            }
            return new BigDecimal("1.2");
        }
    },
    D("D") {
        @Override
        public BigDecimal calcularTaxa(Transferencia transferencia) {
            if (transferencia.getValor().compareTo(new BigDecimal("25000")) <= 0) {
                return A.calcularTaxa(transferencia);
            }
            if (transferencia.getValor().compareTo(new BigDecimal("120000")) <= 0) {
                return B.calcularTaxa(transferencia);
            }
            return C.calcularTaxa(transferencia);
        }
    };

    private final String tipo;

    private TipoTransferencia(String tipo) {
        this.tipo = tipo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.tipo;
    }

}
