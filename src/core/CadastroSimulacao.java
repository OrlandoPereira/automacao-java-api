package core;

import com.github.javafaker.Faker;
import core.entidade.Simulacoes;
import core.enums.SimulacoesEnum;
import core.utils.GeraCpf;

public class CadastroSimulacao{
    private Faker faker = new Faker();
    private Simulacoes simulacao = new Simulacoes();
    private GeraCpf CPF = new GeraCpf();
    public Object cadastroComSeguro(){
        simulacao.setCpf(Long.parseLong(CPF.cpfValido()));
        simulacao.setNome(faker.name().name());
        simulacao.setEmail(faker.internet().emailAddress());
        simulacao.setValor(25000);
        simulacao.setParcelas(4);
        simulacao.setSeguro(true);

        return simulacao;
    }

    public Object cadastroSemSeguro(){
        simulacao.setCpf(Long.parseLong(CPF.cpfValido()));
        simulacao.setNome(faker.name().name());
        simulacao.setEmail(faker.internet().emailAddress());
        simulacao.setValor(5600);
        simulacao.setParcelas(6);
        simulacao.setSeguro(false);

        return simulacao;
    }

    public Object cadastroParametrizadoEditaCampo(SimulacoesEnum name, String valeu){
        /* Serão passados como String e convertidos ao serem passados  */
        // CPF
        if (name == SimulacoesEnum.CPF){simulacao.setCpf(Long.parseLong(valeu));}
        else {simulacao.setCpf(Long.parseLong(CPF.cpfValido()));}
        // Nome
        if (name == SimulacoesEnum.NOME){simulacao.setNome(valeu);}
        else {simulacao.setNome(faker.name().name());}
        // Email
        if (name == SimulacoesEnum.EMAIL){simulacao.setEmail(valeu);}
        else {simulacao.setEmail(faker.internet().emailAddress());}
        // Valor
        if (name == SimulacoesEnum.VALOR){simulacao.setValor(Integer.parseInt(valeu));}
        else {simulacao.setValor(5600);}
        // Parcelas
        if (name == SimulacoesEnum.PARCELAS){simulacao.setParcelas(Integer.parseInt(valeu));}
        else {simulacao.setParcelas(6);}
        // Seguro
        if (name == SimulacoesEnum.SEGURO){
            if (valeu.equalsIgnoreCase("true")){simulacao.setSeguro(true);}}
            else if (valeu.equalsIgnoreCase("false")) {simulacao.setSeguro(true);}
        else {simulacao.setSeguro(true);}

        return simulacao;
    }

    public Object cadastroParametrizadoExcluiCampo(SimulacoesEnum name){
        /* Serão passado o campo que não será enviado no playload */
        // CPF
        if (name != SimulacoesEnum.CPF){simulacao.setCpf(Long.parseLong(CPF.cpfValido()));}
        // Nome
        if (name != SimulacoesEnum.NOME){simulacao.setNome(faker.name().name());}
        // Email
        if (name != SimulacoesEnum.EMAIL){simulacao.setEmail(faker.internet().emailAddress());}
        // Valor
        if (name != SimulacoesEnum.VALOR){simulacao.setValor(5600);}
        // Parcelas
        if (name != SimulacoesEnum.PARCELAS){simulacao.setParcelas(6);}
        // Seguro
        if (name != SimulacoesEnum.SEGURO){simulacao.setSeguro(true);}

        return simulacao;
    }

    public Object cadastroParametrizadoSelecionaCampo(Object[] names){
        /* Serão passado um ou mais campos que serão enviado no playload */
        for (Object name : names) {
            System.out.println(name);
            // CPF
            if (name == SimulacoesEnum.CPF) {
                simulacao.setCpf(Long.parseLong(CPF.cpfValido()));
            }
            // Nome
            if (name == SimulacoesEnum.NOME) {
                simulacao.setNome(faker.name().name());
            }
            // Email
            if (name == SimulacoesEnum.EMAIL) {
                simulacao.setEmail(faker.internet().emailAddress());
            }
            // Valor
            if (name == SimulacoesEnum.VALOR) {
                simulacao.setValor(5600);
            }
            // Parcelas
            if (name == SimulacoesEnum.PARCELAS) {
                simulacao.setParcelas(6);
            }
            // Seguro
            if (name == SimulacoesEnum.SEGURO) {
                simulacao.setSeguro(true);
            }
        }
        return simulacao;
    }

    public static void main(String[] args) {
        CadastroSimulacao cadastroSimulacao = new CadastroSimulacao();
        Object[] sim = {SimulacoesEnum.SEGURO, SimulacoesEnum.CPF};
        cadastroSimulacao.cadastroParametrizadoSelecionaCampo(new SimulacoesEnum[]{SimulacoesEnum.SEGURO, SimulacoesEnum.CPF});
    }



}
