package core;

import core.entidade.Simulacoes;
import core.utils.GeraCpf;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.util.List;

import static core.ApiPath.*;
import static io.restassured.RestAssured.*;

public class BaseTest implements ApiPathBase {

    @BeforeClass
    public static void  setup(){
        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.port = APP_PORT;

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @DataProvider
    public Object[][] DT_CPF_COM_RESTRICAO(ITestContext context) {
        String parameter = context.getCurrentXmlTest().getLocalParameters().get("CPF_COM_RESTRICAO");
        System.out.println(parameter);
        String[] names = parameter.split(",");
        Object[][] returnValues = new Object[names.length][1];
        int index = 0;
        for (Object[] each : returnValues) {
            each[0] = names[index++].trim();
        }
        return returnValues;
    }

    @DataProvider(name = "DP_CADASTRO_SIMULACAO")
    public Object[][] DP_CADASTRO_SIMULACAO(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        Object[][] data = {{simulacao.cadastroComSeguro()}};
        return data;
    }

    public Response criarSimulacao(){
        CadastroSimulacao simulacao = new CadastroSimulacao();
        return given()
                .body(simulacao.cadastroSemSeguro())
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .statusCode(201)
                .extract().response();
    }

    public void criarSimulacao(Integer qtd){
        for (Integer i = 0; i < qtd; i++) {
            CadastroSimulacao simulacao = new CadastroSimulacao();
            given()
                    .body(simulacao.cadastroSemSeguro())
                    .when()
                    .post(CRIAR_SIMULACAO)
                    .then()
                    .statusCode(201);
        }
    }

    public Simulacoes criarSimulacaoRetornoEntidade(){
        Simulacoes simulacao = new CadastroSimulacao().cadastroSemSeguro();
        given()
                .body(simulacao)
                .when()
                .post(CRIAR_SIMULACAO)
                .then()
                .statusCode(201)
                .extract().response();
        return simulacao;
    }

    public List<Integer> consultaTodasSimulacoes(){
        Response response = given()
                .when()
                .get(CONSULTA_TODAS_SIMULACOES)
                .then()
                .statusCode(200)
                .extract().response();
        return response.path("id");

    }

    public void deletarRegistrosSimulacao(List<Integer> ids){
        for (Integer id: ids){
            given()
                    .when()
                    .delete(REMOVER_SIMULACAO, id)
                    .then()
                    .statusCode(200);
        }
    }




}
