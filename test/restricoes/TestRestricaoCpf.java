package restricoes;

import com.sun.org.glassfish.gmbal.Description;
import core.BaseTest;
import static core.ApiPath.CONSULTA_RESTRICAO_POR_CPF;
import static core.ConstantsResponse.MSG_CPF_COM_RESTRICAO;

import core.utils.GeraCpf;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;

public class TestRestricaoCpf extends BaseTest {
    private GeraCpf CPF = new GeraCpf();

    @Test(dataProvider = "DT_CPF_COM_RESTRICAO", description="Invalid Login Scenario with wrong username and password.")
    @Description("Test Description: Login test with wrong username and wrong password.")
    public void testConsultarCpfComRestricao (String CPF){
        given()
                .when()
                .get(CONSULTA_RESTRICAO_POR_CPF, CPF)
                .then()
                .log().body()
                .statusCode(200) // não esquecer de ajustar o retorno que no doc é diferente do retornado
                .body("mensagem", is(String.format(MSG_CPF_COM_RESTRICAO, CPF)));

    }


    @Test
    public void testConsultarCpfSemRestricao(){
        given()
                .when()
                .get(CONSULTA_RESTRICAO_POR_CPF, CPF.cpfValido())
                .then()
                .log().body()
                .statusCode(204);
    }

    @Test
    public void testConsultarCpfInvalido(){
        given()
                .when()
                .get(CONSULTA_RESTRICAO_POR_CPF, CPF.cpfInvalido())
                .then()
                .log().body()
                .statusCode(204);
    }




}
