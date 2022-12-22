package simulacoes.criacao;

import core.BaseTest;
import static core.ApiPath.CRIAR_SIMULACAO;
import core.CadastroSimulacao;
import core.entidade.Simulacoes;
import core.enums.SimulacoesEnum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestCriacaoSimulacoes extends BaseTest{

    @Test(dataProvider = "DP_CADASTRO_SIMULACAO")
    public void testCriarSimulacaoComSeguroComSucesso(Object simulacaoComSeguro){
        given()
                .body(simulacaoComSeguro)
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(201);
    }


    @Test
    public void testCriarSimulacaoSemSeguroComSucesso(){
        Simulacoes simulacao = new CadastroSimulacao().cadastroSemSeguro();

        given()
                .body(simulacao)
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(201)
                .body("nome", is(simulacao.getNome()))
                .body("cpf", is(simulacao.getCpf().toString()))
                .body("email", is(simulacao.getEmail()))
                .body("valor", is(simulacao.getValor()))
                .body("parcelas", is(simulacao.getParcelas()))
                .body("seguro", is(simulacao.getSeguro()));
    }

    @Test
    public void testCriarSimulacaoValorMinimoComSucesso(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer valor = 1000;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.VALOR,valor.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(201)
                .body("valor", is(valor));
    }

    @Test
    public void testCriarSimulacaoValorMaximoComSucesso() {
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer valor = 40000;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.VALOR, valor.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(201)
                .body("valor", is(valor));
    }


    @Test
    public void testCriarSimulacaoParcelaMinimaComSucesso(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer numParcela = 2;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.PARCELAS,numParcela.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(201)
                .body("parcelas", is(numParcela));
    }

    @Test
    public void testCriarSimulacaoParcelaMaximoComSucesso(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer numParcela = 48;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.PARCELAS,numParcela.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(201)
                .body("parcelas", is(numParcela));
    }





}
