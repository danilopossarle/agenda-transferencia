package agendaTransferencias.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe utilitária para manipulação do {@link TipoTransferencia};
 * 
 * @author danilo.possarle
 * @created Dec 14, 2015
 */
public class TipoTransferenciaUtils {

    /**
     * Cria a lista de escolhas possíveis para tipo de transferência
     */
    public static Map<TipoTransferencia, String> getTipoTransferenciaChoices() {
        Map<TipoTransferencia, String> tipoTransferenciaChoices = new LinkedHashMap<TipoTransferencia, String>();
        tipoTransferenciaChoices.put(null, "Selecione");
        for (TipoTransferencia tipo : TipoTransferencia.values()) {
            tipoTransferenciaChoices.put(tipo, tipo.toString());
        }
        return tipoTransferenciaChoices;
    }

}
