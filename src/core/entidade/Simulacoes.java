package core.entidade;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;


@Getter
@Setter
public class Simulacoes {
 private Long cpf;
 private String  nome;
 private String email;
 private Integer valor;
 private Integer parcelas;
 private Boolean seguro;


}


