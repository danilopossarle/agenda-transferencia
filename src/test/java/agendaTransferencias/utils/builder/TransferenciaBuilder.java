package agendaTransferencias.utils.builder;

import static agendaTransferencias.utils.TipoTransferencia.A;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.utils.TipoTransferencia;

/**
 * {@link Builder} para {@link Transferencia}
 * 
 * @author danilo.possarle
 * @created Dec 17, 2015
 */
public class TransferenciaBuilder implements Builder<Transferencia> {

	private Transferencia transferencia = new Transferencia();
	
	public static TransferenciaBuilder umaTransferencia() {
		DateTime dataCadastro = new DateTime().withTimeAtStartOfDay();
		DateTime dataTransferencia = dataCadastro.plusDays(10);
		TransferenciaBuilder builder = new TransferenciaBuilder();
		builder.comContaDestino("12345-6").comContaOrigem("65432-1")
				.comDataCadastro(dataCadastro).comDataTransferencia(dataTransferencia)
				.comTaxa(ONE).comValor(BigDecimal.TEN).comTipo(A);
		
		return builder;
	}
	
	public TransferenciaBuilder comContaDestino(String contaDestino) {
		this.transferencia.setContaDestino(contaDestino); 
		return this;
	}
	
	public TransferenciaBuilder comContaOrigem(String contaOrigem) {
		this.transferencia.setContaOrigem(contaOrigem); 
		return this;
	}
	
	public TransferenciaBuilder comDataCadastro(DateTime dataCadastro) {
		this.transferencia.setDataCadastro(dataCadastro); 
		return this;
	}
	
	public TransferenciaBuilder comDataTransferencia(DateTime dataTransferencia) {
		this.transferencia.setDataTransferencia(dataTransferencia); 
		return this;
	}

	public TransferenciaBuilder comTaxa(BigDecimal taxa) {
		this.transferencia.setTaxa(taxa); 
		return this;
	}

	public TransferenciaBuilder comValor(BigDecimal valor) {
		this.transferencia.setValor(valor); 
		return this;
	}
	
	public TransferenciaBuilder comTipo(TipoTransferencia tipo) {
		this.transferencia.setTipo(tipo);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Transferencia build() {
		return transferencia;
	}
	
}
