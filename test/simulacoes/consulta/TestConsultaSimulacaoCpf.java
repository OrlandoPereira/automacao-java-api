package simulacoes.consulta;

import core.BaseTest;
import core.ConstantsResponse;
import core.entidade.Simulacoes;
import org.testng.annotations.Test;

import static core.ApiPath.CONSULTAR_SIMLACAO_POR_CPF;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestConsultaSimulacaoCpf extends BaseTest {
    @Test
    public void testConsultaSimulacaoCadastradaValidandoCamposComSucesso(){
        Simulacoes simulacoes = criarSimulacaoRetornoEntidade();

        given()
                .when()
                .get(CONSULTAR_SIMLACAO_POR_CPF, simulacoes.getCpf())
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(simulacoes.getNome()))
                .body("cpf", is(simulacoes.getCpf().toString()))
                .body("email", is(simulacoes.getEmail()))
                .body("valor", is(simulacoes.getValor().floatValue()))
                .body("parcelas", is(simulacoes.getParcelas()))
                .body("seguro", is(simulacoes.getSeguro()));
    }

    @Test
    public void testConsultaSimulacaoSemCadastroRetornaErro(){
        String cpf = "99900000666";

        given()
                .when()
                .get(CONSULTAR_SIMLACAO_POR_CPF, cpf)
                .then()
                .log().body()
                .statusCode(404)
                .body("mensagem", is(String.format(ConstantsResponse.MSG_CPF_NAO_ENCONTRADO, cpf)));
    }
    
}
