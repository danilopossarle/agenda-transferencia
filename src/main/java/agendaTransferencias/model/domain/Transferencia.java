package agendaTransferencias.model.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import agendaTransferencias.utils.TipoTransferencia;

import com.sun.istack.internal.NotNull;

/**
 * Entidade que representa uma transferência dentro da aplicação
 * 
 * @author danilo.possarle
 * @created Dec 9, 2015
 */
@Entity
@Table(name = "TRANSFERENCIA")
public class Transferencia extends Persistable {

    private String contaOrigem;

    private String contaDestino;

    private BigDecimal valor;

    private BigDecimal taxa;

    private DateTime dataCadastro = new DateTime();

    private DateTime dataTransferencia;

    private TipoTransferencia tipo;

    /**
     * @return the contaOrigem
     */
    @Column(name = "CTA_ORIGEM")
    @NotNull
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
    @Column(name = "CTA_DESTINO")
    @NotNull
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
     * @return the valor
     */
    @Column(name = "VALOR")
    @NotNull
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
     * @return the taxa
     */
    @Column(name = "TAXA")
    @NotNull
    public BigDecimal getTaxa() {
        return this.taxa;
    }

    /**
     * @param taxa the taxa to set
     */
    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    /**
     * @return the dataCadastro
     */
    @Column(name = "DATA_CADASTRO")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @NotNull
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
     * @return the dataTransferencia
     */
    @Column(name = "DATA_TRANSFERENCIA")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @NotNull
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
     * @return the tipo
     */
    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    @NotNull
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
