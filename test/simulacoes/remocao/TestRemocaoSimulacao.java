package simulacoes.remocao;

import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static core.ApiPath.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestRemocaoSimulacao extends BaseTest {

    @Test
    public void testRemoverSimulacaoComSucesso(){
        Response id = criarSimulacao();

        given()
                .when()
                .delete(REMOVER_SIMULACAO, (Object) id.path("id"))
                .then()
                .log().body()
                .statusCode(204); // No pdf diz 204, no swagger diz 200
    }

    @Test
    public void testRetornarErroAoRemoverSimulacaoNaoExistente(){
        Integer id = 999999;

        given()
                .when()
                .delete(REMOVER_SIMULACAO, id)
                .then()
                .log().body()
                .statusCode(404)
                .body("mensagem",is("Simulação não encontrada"));
    }
}
