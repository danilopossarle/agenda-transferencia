package agendaTransferencias.utils.validator;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final Pattern pattern = Pattern.compile("\\d{5,5}-\\d{1,1}");

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

        if (this.nullCheck(transferencia)) {
            return;
        }

        this.validaFormatacaoConta(transferencia.getContaOrigem(), "contaOrigem", errors);
        this.validaFormatacaoConta(transferencia.getContaDestino(), "contaDestino", errors);
        this.validaContasDiferentes(errors, transferencia);

        this.validaDatas(errors, transferencia);

    }

    /**
     * Valida se a data de cadastro é posterior à data de transferência
     * 
     * @param errors {@link Errors} onde deve ser adicionado o erro caso a formatação seja violada
     * @param transferencia {@link Transferencia} a ser validada
     */
    private void validaDatas(Errors errors, Transferencia transferencia) {
        if (!transferencia.getDataCadastro().isBefore(transferencia.getDataTransferencia())) {
            errors.rejectValue("dataTransferencia", "dataTransferencia.after.dataCadastro", "A data de transferência deve ser maior do que a data de cadastro");
        }
    }

    /**
     * Valida se as contas de origem e destino são diferentes
     * 
     * @param errors {@link Errors} onde deve ser adicionado o erro caso a formatação seja violada
     * @param transferencia {@link Transferencia} a ser validada
     */
    private void validaContasDiferentes(Errors errors, Transferencia transferencia) {
        if (transferencia.getContaDestino().equals(transferencia.getContaOrigem())) {
            errors.rejectValue("contaDestino", "contas.devem.ser.diferentes", "As contas de origem e destino devem ser diferentes");
            errors.rejectValue("contaOrigem", "contas.devem.ser.diferentes", "As contas de origem e destino devem ser diferentes");
        }
    }

    /**
     * Valida se a conta está seguindo o formato XXXXX-X
     * 
     * @param conta a conta a ser validada
     * @param field o campo que está sendo validado
     * @param errors {@link Errors} onde deve ser adicionado o erro caso a formatação seja violada
     */
    private void validaFormatacaoConta(String conta, String field, Errors errors) {
        Matcher matcher = this.pattern.matcher(conta);
        if (conta.length() != 7 || !matcher.find()) {
            errors.rejectValue(field, "conta.mal.formatada", "A conta deve ter o formato XXXXX-X");
        }
    }

    /**
     * Verifica se os campos necessários para que não ocorra um null pointer no validator estão preenchidos
     * 
     * @param transferencia {@link Transferencia}
     * @return <code>true</code> caso exista algum campo obrigatório que esteja nulo, <code>false</code> caso contrário.
     */
    private boolean nullCheck(Transferencia transferencia) {
        return transferencia.getDataTransferencia() == null || transferencia.getDataCadastro() == null
                || isEmpty(transferencia.getContaDestino()) || isEmpty(transferencia.getContaOrigem());
    }

}
