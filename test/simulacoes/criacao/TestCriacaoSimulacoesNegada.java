package simulacoes.criacao;

import core.BaseTest;
import core.CadastroSimulacao;
import core.enums.SimulacoesEnum;
import org.testng.annotations.Test;

import static core.ConstantsResponse.*;
import static core.ApiPath.CRIAR_SIMULACAO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestCriacaoSimulacoesNegada extends BaseTest {

    @Test
    public void testNaoCriarSimulacaoComEmailInvalido(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        String email = "email.invalido";
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.EMAIL, email))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.email", is(MSG_NAO_E_UM_ENDERECO_DE_EMAIL_VALIDO));
    }

    @Test
    public void testNaoCriarSimulacaoComValorMenorQueMinimo(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer valor = 999;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.VALOR, valor.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.valor", is("cenario com erro"));
    }

    @Test
    public void testNaoCriarSimulacaoComValorMaiorQueMaximo(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer valor = 40001;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.VALOR, valor.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.valor", is(MSG_VALOR_DEVE_SER_MENOR_OU_IGUAL_A_R$40000));
    }

    @Test
    public void testNaoCriarSimulacaoComParcelasMenorQueMinimo(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer parcelas = 1;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.PARCELAS, parcelas.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.parcelas", is(MSG_PARCELAS_DEVE_SER_IGUAL_OU_MAIOR_QUE_2));
    }

    @Test
    public void testNaoCriarSimulacaoComParcelasMaiorQueMaximo(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Integer parcelas = 49;
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.PARCELAS, parcelas.toString()))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(400)
                .body("erros.parcelas", is("cenario com erro"));
    }

    @Test
    public void testNaoCriarSimulacaoComCpfExistente(){
        Object cpf = criarSimulacao();
        CadastroSimulacao simulacao = new CadastroSimulacao();
        given()
                .body(simulacao.cadastroParametrizadoEditaCampo(SimulacoesEnum.CPF, (String) cpf))
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .log().body()
                .statusCode(409)
                .body("mensagem", is("cenario com erro"));
    }

}
