package agendaTransferencias.controller.transferencia;

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
        if (transferencia.getDataTransferencia().isBefore(transferencia.getDataCadastro())) {
            errors.rejectValue("dataTransferencia", "dataTransferencia.after.dataCadastro", "A data de transferência deve ser maior do que a data de cadastro ");
        }

    }

}
