package agendaTransferencias.utils.builder;

/**
 * Interface para a implementação de um builder. 
 *
 * @param <T> tipo a ser contruído
 * @author danilo.possarle
 * @created Dec 17, 2015
 */
public interface Builder<T> {

	/**
	 * Constrói
	 * 
	 * @return instância construída.
	 */
	T build();
}
