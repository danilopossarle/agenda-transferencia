package agendaTransferencias.utils.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import agendaTransferencias.utils.TipoTransferencia;

/**
 * Model para a pesquisa de transferências
 * 
 * @author danilo.possarle
 * @created Dec 12, 2015
 */
public class PesquisaTranferenciaModel implements Serializable {

    private String contaOrigem;

    private String contaDestino;

    private DateTime dataTransferencia;

    private DateTime dataCadastro;

    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal valor;

    private TipoTransferencia tipo;

    /**
     * @return the contaOrigem
     */
    public String getContaOrigem() {
        return this.contaOrigem;
    }

    /**
     * @param contaOrigem the contaOrigem to set
     */
    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    /**
     * @return the contaDestino
     */
    public String getContaDestino() {
        return this.contaDestino;
    }

    /**
     * @param contaDestino the contaDestino to set
     */
    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    /**
     * @return the dataTransferencia
     */
    public DateTime getDataTransferencia() {
        return this.dataTransferencia;
    }

    /**
     * @param dataTransferencia the dataTransferencia to set
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public void setDataTransferencia(DateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    /**
     * @return the dataCadastro
     */
    public DateTime getDataCadastro() {
        return this.dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public void setDataCadastro(DateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return this.valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the tipo
     */
    public TipoTransferencia getTipo() {
        return this.tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoTransferencia tipo) {
        this.tipo = tipo;
    }

}
