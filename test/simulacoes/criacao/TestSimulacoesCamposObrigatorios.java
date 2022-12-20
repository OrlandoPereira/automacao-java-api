package simulacoes.criacao;

import core.BaseTest;
import core.CadastroSimulacao;
import core.enums.SimulacoesEnum;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static core.ApiPath.CRIAR_SIMULACAO;
import static core.ConstantsResponse.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestSimulacoesCamposObrigatorios extends BaseTest {

    @Test
    public void testRetornarErroCampoObrigarotorioCpf(){
        CadastroSimulacao simulacao = new CadastroSimulacao();

        given()
                .body(simulacao.cadastroParametrizadoExcluiCampo(SimulacoesEnum.CPF))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.cpf", is(MSG_CPF_NAO_PODE_SER_VAZIO));
    }

    @Test
    public void testRetornarErroCampoObrigarotorioNome(){
        CadastroSimulacao simulacao = new CadastroSimulacao();

        given()
                .body(simulacao.cadastroParametrizadoExcluiCampo(SimulacoesEnum.NOME))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.nome", is(MSG_NOME_NAO_PODE_SER_VAZIO));
    }

    @Test
    public void testRetornarErroCampoObrigarotorioEmail(){
        CadastroSimulacao simulacao = new CadastroSimulacao();

        given()
                .body(simulacao.cadastroParametrizadoExcluiCampo(SimulacoesEnum.EMAIL))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.email", is(MSG_EMAIL_NAO_PODE_SER_VAZIO));
    }

    @Test
    public void testRetornarErroCampoObrigarotorioValor(){
        CadastroSimulacao simulacao = new CadastroSimulacao();

        given()
                .body(simulacao.cadastroParametrizadoExcluiCampo(SimulacoesEnum.VALOR))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.valor", is(MSG_VALOR_NAO_PODE_SER_VAZIO));
    }

    @Test
    public void testRetornarErroCampoObrigarotorioParcelas(){
        CadastroSimulacao simulacao = new CadastroSimulacao();

        given()
                .body(simulacao.cadastroParametrizadoExcluiCampo(SimulacoesEnum.PARCELAS))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.parcelas", is(MSG_PARCELAS_NAO_PODE_SER_VAZIO));
    }

    @Test
    public void testRetornarErroCampoObrigarotorioSeguro(){
        CadastroSimulacao simulacao = new CadastroSimulacao();

        given()
                .body(simulacao.cadastroParametrizadoExcluiCampo(SimulacoesEnum.SEGURO))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.seguro", is(MSG_SEGURO_NAO_PODE_SER_VAZIO));
    }

    @Test
    public void testRetornarErroMaisDeUmCampoObrigarotorio(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Object camposEnviados = simulacao.cadastroParametrizadoSelecionaCampo(
                new SimulacoesEnum[]{SimulacoesEnum.CPF, SimulacoesEnum.SEGURO});
        given()
                .body(camposEnviados)
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.parcelas", is(MSG_PARCELAS_NAO_PODE_SER_VAZIO))
                .body("erros.valor",is(MSG_VALOR_NAO_PODE_SER_VAZIO))
                .body("erros.nome", is(MSG_NOME_NAO_PODE_SER_VAZIO))
                .body("erros.email", is(MSG_EMAIL_NAO_PODE_SER_VAZIO));
    }

}
