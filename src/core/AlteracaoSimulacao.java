package core;

import core.entidade.Simulacoes;
import core.enums.SimulacoesEnum;

import java.util.Hashtable;
import java.util.Map;

public class AlteracaoSimulacao {
    public Simulacoes simulacao = new Simulacoes();

    public Simulacoes alterarParametrizadoSelecionaCamposeValores(Hashtable<SimulacoesEnum, String> my_dict){
        /* Serão passado um ou mais campos com valor que serão enviado no playload */
        for (Map.Entry m:my_dict.entrySet()) {
            // CPF
            if (m.getKey() == SimulacoesEnum.CPF) {
                simulacao.setCpf(Long.parseLong((String) m.getValue()));
            }
            // Nome
            if (m.getKey() == SimulacoesEnum.NOME) {
                simulacao.setNome((String) m.getValue());
            }
            // Email
            if (m.getKey() == SimulacoesEnum.EMAIL) {
                simulacao.setEmail((String) m.getValue());
            }
            // Valor
            if (m.getKey() == SimulacoesEnum.VALOR) {
                simulacao.setValor(Integer.parseInt((String) m.getValue()));
            }
            // Parcelas
            if (m.getKey() == SimulacoesEnum.PARCELAS) {
                simulacao.setParcelas(Integer.parseInt((String) m.getValue()));
            }
            // Seguro
            if (m.getKey() == SimulacoesEnum.SEGURO) {
                if (m.getValue().equals("true")) {simulacao.setSeguro(true);
                } else if (m.getValue().equals("false")){simulacao.setSeguro(false);
                } else {simulacao.setSeguro(true);}
            }
        }
        return simulacao;
    }



}
