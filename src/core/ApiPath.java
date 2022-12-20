package core;


public interface ApiPath {

    String CONSULTA_RESTRICAO_POR_CPF = "/api/v1/restricoes/{cpf}";
    String CRIAR_SIMULACAO = "/api/v1/simulacoes";
    String ALTERAR_SIMULACAO = "/api/v1/simulacoes/{cpf}";
    String CONSULTA_TODAS_SIMULACOES = "/api/v1/simulacoes";
    String CONSULTAR_SIMLACAO_POR_CPF = "/api/v1/simulacoes/{cpf}";
    String REMOVER_SIMULACAO = "/api/v1/simulacoes/{id}";

}
