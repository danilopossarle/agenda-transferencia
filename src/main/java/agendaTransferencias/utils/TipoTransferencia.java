package agendaTransferencias.utils;


/**
 * Enum que representa os tipos de transferências aceitos na aplicação
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
public enum TipoTransferencia {

    A("A"),
    B("B"),
    C("C"),
    D("D");

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
