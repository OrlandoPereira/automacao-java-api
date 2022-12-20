package core;

public interface ConstantsResponse {

    /* Retorno usado na consulta de Restrição Cpf*/
    String MSG_CPF_COM_RESTRICAO = "O CPF %s possui restrição";

    /* Retornos usados na criacao de simulações  */
    /* CAMPOS OBRIGATORIOS*/
    String MSG_NOME_NAO_PODE_SER_VAZIO = "Nome não pode ser vazio";
    String MSG_CPF_NAO_PODE_SER_VAZIO = "CPF não pode ser vazio";
    String MSG_EMAIL_NAO_PODE_SER_VAZIO = "E-mail não deve ser vazio";
    String MSG_VALOR_NAO_PODE_SER_VAZIO = "Valor não pode ser vazio";
    String MSG_PARCELAS_NAO_PODE_SER_VAZIO = "Parcelas não pode ser vazio";
    String MSG_SEGURO_NAO_PODE_SER_VAZIO = "Seguro não pode ser vazio";
    String MSG_NAO_E_UM_ENDERECO_DE_EMAIL_VALIDO = "E-mail deve ser um e-mail válido";

    /* Retorno de msg de Maximo e Minimo */
    String MSG_VALOR_DEVE_SER_MENOR_OU_IGUAL_A_R$40000 = "Valor deve ser menor ou igual a R$ 40.000";
    String MSG_PARCELAS_DEVE_SER_IGUAL_OU_MAIOR_QUE_2 = "Parcelas deve ser igual ou maior que 2";

}
