package simulacoes.alteracao;

import core.AlteracaoSimulacao;
import core.BaseTest;
import core.entidade.Simulacoes;
import core.enums.SimulacoesEnum;
import org.testng.annotations.Test;

import java.util.*;

import static core.ApiPath.ALTERAR_SIMULACAO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestAlteracaoSimulacao extends BaseTest {

    @Test
    public void testAlterarSimulacaoComVariosCamposComSucesso(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.NOME, "joaozito mato");
        my_dict.put(SimulacoesEnum.EMAIL, "joao@email.com");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(alteracaoSimulacao.simulacao.getNome()))
                .body("cpf", is(simulacoes.getCpf().toString()))
                .body("email", is(alteracaoSimulacao.simulacao.getEmail()))
                .body("valor", is(simulacoes.getValor().floatValue()))
                .body("parcelas", is(simulacoes.getParcelas()))
                .body("seguro", is(alteracaoSimulacao.simulacao.getSeguro()));
    }

    @Test
    public void testCriarSimulacaoValorMinimoComSucesso(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.VALOR, "1000");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(simulacoes.getNome()))
                .body("cpf", is(simulacoes.getCpf().toString()))
                .body("email", is(simulacoes.getEmail()))
                .body("valor", is(alteracaoSimulacao.simulacao.getValor().floatValue()))
                .body("parcelas", is(simulacoes.getParcelas()))
                .body("seguro", is(simulacoes.getSeguro()));

    }

    @Test
    public void testCriarSimulacaoValorMaximoComSucesso() {
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.VALOR, "40000");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(simulacoes.getNome()))
                .body("cpf", is(simulacoes.getCpf().toString()))
                .body("email", is(simulacoes.getEmail()))
                .body("valor", is(alteracaoSimulacao.simulacao.getValor().floatValue()))
                .body("parcelas", is(simulacoes.getParcelas()))
                .body("seguro", is(simulacoes.getSeguro()));
    }


    @Test
    public void testCriarSimulacaoParcelaMinimaComSucesso(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.PARCELAS, "2");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(simulacoes.getNome()))
                .body("cpf", is(simulacoes.getCpf().toString()))
                .body("email", is(simulacoes.getEmail()))
                .body("valor", is(simulacoes.getValor().floatValue()))
                .body("parcelas", is(alteracaoSimulacao.simulacao.getParcelas()))
                .body("seguro", is(simulacoes.getSeguro()));
    }

    @Test
    public void testCriarSimulacaoParcelaMaximoComSucesso(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();
        my_dict.put(SimulacoesEnum.PARCELAS, "48");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(simulacoes.getNome()))
                .body("cpf", is(simulacoes.getCpf().toString()))
                .body("email", is(simulacoes.getEmail()))
                .body("valor", is(simulacoes.getValor().floatValue()))
                .body("parcelas", is(alteracaoSimulacao.simulacao.getParcelas()))
                .body("seguro", is(simulacoes.getSeguro()));
    }






}
