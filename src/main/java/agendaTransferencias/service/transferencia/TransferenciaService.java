package agendaTransferencias.service.transferencia;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agendaTransferencias.model.dao.TransferenciaDAO;
import agendaTransferencias.model.domain.Transferencia;
import agendaTransferencias.utils.calculadora.CalculadoraTaxa;
import agendaTransferencias.utils.factory.CalculadoraTaxaFactory;
import agendaTransferencias.utils.model.PesquisaTransferenciaModel;

/**
 * Serviço para transferência
 * 
 * @author danilo.possarle
 * @created Dec 12, 2015
 */
@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaDAO transferenciaDAO;

    @Autowired
    private CalculadoraTaxaFactory calculadoraFactory;

    /**
     * Realiza a busca com base no model informado.
     * 
     * @param pesquisaModel {@link PesquisaTransferenciaModel}
     * @return {@link List} de {@link Transferencia} de acordo com as informações do model
     */
    public List<Transferencia> findTransferenciasBy(PesquisaTransferenciaModel pesquisaModel) {
        return this.transferenciaDAO.findBy(pesquisaModel.getDataTransferencia(), pesquisaModel.getDataCadastro(),
                pesquisaModel.getContaOrigem(), pesquisaModel.getContaDestino(), pesquisaModel.getValor(), pesquisaModel.getTipo());
    }

    /**
     * Busca todas as transferências do sistema
     * 
     * @return {@link List} de {@link Transferencia}
     */
    public List<Transferencia> findAll() {
        return this.transferenciaDAO.findAll();
    }

    /**
     * Salva a transferência
     * 
     * @param transferencia {@link Transferencia}
     */
    @Transactional
    public void save(Transferencia transferencia) {
        this.transferenciaDAO.save(transferencia);
    }

    /**
     * Remove uma transferência da base de dados.
     * 
     * @param idTransferencia o id da transferência que deve ser removida da base
     */
    @Transactional
    public void remove(Long idTransferencia) {
        this.transferenciaDAO.remove(idTransferencia);

    }

    /**
     * Realiza o calculo da taxa para uma {@link Transferencia}
     * 
     * @param transferencia {@link Transferencia} para a qual a taxa deve ser calculada
     * @return a taxa calculada
     */
    public BigDecimal calculaTaxa(Transferencia transferencia) {
        CalculadoraTaxa calculadora = this.calculadoraFactory.calculadoraPara(transferencia.getTipo());
        if (calculadora != null) {
            return calculadora.calcularTaxa(transferencia);
        }
        return ZERO;
    }

}
