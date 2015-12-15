package agendaTransferencias.model.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import agendaTransferencias.utils.TipoTransferencia;

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

    private DateTime dataCadastro = new DateTime().withTimeAtStartOfDay();

    private DateTime dataTransferencia;

    private TipoTransferencia tipo;

    /**
     * @return the contaOrigem
     */
    @Column(name = "CTA_ORIGEM")
    @NotNull
    @NotEmpty(message="O campo Conta Origem obrigatório.")
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
    @NotEmpty(message="O campo Conta Destino obrigatório.")
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
    @NotNull(message="Campo Valor é obrigatório")
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
    @NotNull(message="Campo Taxa é obrigatório")
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
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @NotNull(message="Campo Data Cadastro é obrigatório")
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
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @NotNull(message="Campo Data Transferencia é obrigatório")
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
    @NotNull(message="Campo Tipo é obrigatório")
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
