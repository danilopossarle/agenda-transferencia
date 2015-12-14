package agendaTransferencias.utils.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.DateTime;

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
	
	private BigDecimal valor;
	
	private TipoTransferencia tipo;

	/**
	 * @return the contaOrigem
	 */
	public String getContaOrigem() {
		return contaOrigem;
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
		return contaDestino;
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
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia the dataTransferencia to set
	 */
	public void setDataTransferencia(DateTime dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	/**
	 * @return the dataCadastro
	 */
	public DateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
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
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoTransferencia tipo) {
		this.tipo = tipo;
	}
	
	
	
}
