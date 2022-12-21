package simulacoes.alteracao;

import core.AlteracaoSimulacao;
import core.BaseTest;
import core.entidade.Simulacoes;
import core.enums.SimulacoesEnum;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static core.ApiPath.ALTERAR_SIMULACAO;
import static core.ConstantsResponse.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestAlteracaoNegada extends BaseTest {

    @Test
    public void testNaoAlterarSimulacaoComEmailInvalido(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.EMAIL, "joaocomemail");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.email", is(MSG_NAO_E_UM_ENDERECO_DE_EMAIL_VALIDO));
    }

    @Test
    public void testNaoAlterarSimulacaoComValorMenorQueMinimo(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.VALOR, "999");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .statusCode(400)
                .body("erros.valor", is("retorna erro"));
    }

    @Test
    public void testNaoAlterarSimulacaoComValorMaiorQueMaximo(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.VALOR, "40001");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .statusCode(400)
                .body("erros.valor", is(MSG_VALOR_DEVE_SER_MENOR_OU_IGUAL_A_R$40000));
    }

    @Test
    public void testNaoAlterarSimulacaoComParcelasMenorQueMinimo(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.PARCELAS, "1");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .statusCode(400)
                .body("erros.parcelas", is(MSG_PARCELAS_DEVE_SER_IGUAL_OU_MAIOR_QUE_2));
    }

    @Test
    public void testNaoAlterarSimulacaoComParcelasMaiorQueMaximo(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.PARCELAS, "49");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .statusCode(400)
                .body("erros.parcelas", is("retorno com erro"));
    }

    @Test
    public void testNaoAlterarSimulacaoComCpfNaoExistente(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        String cpf = "99900000666";
        my_dict.put(SimulacoesEnum.NOME, "Joao teste");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, Long.parseLong(cpf))
                .then()
                .statusCode(404)
                .body("mensagem", is(String.format(MSG_CPF_NAO_ENCONTRADO, cpf)));
    }

}
