package simulacoes.consulta;

import core.BaseTest;
import core.ConstantsResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static core.ApiPath.CONSULTA_TODAS_SIMULACOES;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;

public class TestConsultaSimulacaoTodas extends BaseTest {

    @Test
    public void testConsultaSimulacaoComUmCadastro(){
        List<Integer> ids = consultaTodasSimulacoes();
        deletarRegistrosSimulacao(ids);
        criarSimulacao(1);
        given()
                .when()
                .get(CONSULTA_TODAS_SIMULACOES)
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    public void testConsultaSimulacaoComVariosCadastros(){
        List<Integer> ids = consultaTodasSimulacoes();
        deletarRegistrosSimulacao(ids);
        Integer qtdRegistros = 7;
        criarSimulacao(qtdRegistros);
        given()
                .when()
                .get(CONSULTA_TODAS_SIMULACOES)
                .then()
                .statusCode(200)
                .body("size()", is(qtdRegistros));
    }

    @Test
    public void testConsultaSimulacaoSemCadastroRetornaErro() {
        List<Integer> ids = consultaTodasSimulacoes();
        deletarRegistrosSimulacao(ids);
        given()
                .when()
                .get(CONSULTA_TODAS_SIMULACOES)
                .then()
                .statusCode(204)
                .body("size()", is(0));
    }


}
