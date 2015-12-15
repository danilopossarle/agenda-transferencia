package agendaTransferencias.utils.validator;

import static org.springframework.util.StringUtils.isEmpty;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import agendaTransferencias.model.domain.Transferencia;

/**
 * {@link Validator} para entidade {@link Transferencia}
 * 
 * @author danilo.possarle
 * @created Dec 14, 2015
 */
@Component
public class TransferenciaValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Transferencia.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object target, Errors errors) {
        Transferencia transferencia = Transferencia.class.cast(target);
        
        if(this.temErroDeCamposObrigatorios(errors, transferencia)){
        	return;
        }
        
        if (transferencia.getDataTransferencia().isBefore(transferencia.getDataCadastro())) {
            errors.rejectValue("dataTransferencia", "dataTransferencia.after.dataCadastro", "A data de transferência deve ser maior do que a data de cadastro");
        }
        
        if(transferencia.getContaDestino().equals(transferencia.getContaOrigem())){
        	errors.rejectValue("contaDestino", "contas.devem.ser.diferentes", "As contas de origem e destino devem ser diferentes");
        	errors.rejectValue("contaOrigem", "contas.devem.ser.diferentes", "As contas de origem e destino devem ser diferentes");
        }

    }

    /**
     * Verifica se os campos necessários para que não ocorra um null pointer no validator estão preenchidos
     * 
     * @param errors {@link Errors}
     * @param transferencia {@link Transferencia}
     * @return <code>true</code> caso exista algum campo obrigatório que esteja nulo, <code>false</code> caso contrário.
     */
	private boolean temErroDeCamposObrigatorios(Errors errors, Transferencia transferencia) {
		boolean campoObrigarioNaoPreenchido = false;
		if(transferencia.getDataTransferencia() == null || transferencia.getDataCadastro() == null
				|| isEmpty(transferencia.getContaDestino()) || isEmpty(transferencia.getContaOrigem())){
        	campoObrigarioNaoPreenchido = true;
        }
        return campoObrigarioNaoPreenchido;
	}

}
