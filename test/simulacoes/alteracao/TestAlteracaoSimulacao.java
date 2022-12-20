package simulacoes.alteracao;

import core.AlteracaoSimulacao;
import core.BaseTest;
import core.enums.SimulacoesEnum;
import org.testng.annotations.Test;

import java.util.*;

import static core.ApiPath.ALTERAR_SIMULACAO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestAlteracaoSimulacao extends BaseTest {

    @Test
    public void testNaoCriarSimulacaoComCpfExistente(){
        AlteracaoSimulacao alteracaoSimulacao = new AlteracaoSimulacao();
        Hashtable<SimulacoesEnum, String> my_dict = new Hashtable<>();
        String cpf = criarSimulacao().path("cpf");
        my_dict.put(SimulacoesEnum.NOME, "joaozito mato");
        my_dict.put(SimulacoesEnum.EMAIL, "joao@email.com");
        my_dict.put(SimulacoesEnum.SEGURO, "false");

        given()
                .body(alteracaoSimulacao.alterarParametrizadoSelecionaCamposeValores(my_dict))
                .when()
                .put(ALTERAR_SIMULACAO, (String) cpf)
                .then()
                .log().body()
                .statusCode(200)
                .body("nome", is(alteracaoSimulacao.simulacao.getNome()))
                .body("cpf", is(cpf))
                .body("email", is(alteracaoSimulacao.simulacao.getEmail()))
                .body("valor", is(alteracaoSimulacao.simulacao.getValor()))
                .body("parcelas", is(alteracaoSimulacao.simulacao.getParcelas()))
                .body("seguro", is(alteracaoSimulacao.simulacao.getSeguro()));
    }





}
