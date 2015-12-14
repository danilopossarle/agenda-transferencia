package agendaTransferencias.model.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

    private DateTime dataCadastro = new DateTime();

    private DateTime dataTransferencia;

    private TipoTransferencia tipo;

    /**
     * @return the contaOrigem
     */
    @Column(name = "CTA_ORIGEM")
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
    public DateTime getDataCadastro() {
        return this.dataCadastro;
    }
    
    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
    
    /**
     * @return the dataTransferencia
     */
    @Column(name = "DATA_TRANSFERENCIA")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    public DateTime getDataTransferencia() {
        return this.dataTransferencia;
    }

    /**
     * @param dataTransferencia the dataTransferencia to set
     */
    public void setDataTransferencia(DateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    /**
     * @return the tipo
     */
    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
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
