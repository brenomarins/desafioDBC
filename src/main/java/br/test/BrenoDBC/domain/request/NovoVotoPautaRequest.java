package br.test.BrenoDBC.domain.request;

import br.test.BrenoDBC.domain.enums.TIPOVOTO;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requisicao para registro de novo voto")
public class NovoVotoPautaRequest {
    @NotBlank(message = "Campo 'cpf' não pode ser vazio")
    @NotNull
    @Schema(name = "cpf", description =  "CPF do usuario", required = true)
    private String cpf;

    @NotBlank(message = "Campo 'vote' não pode ser vazio")
    @NotNull
    @Schema(name = "vote", description =  "VOTO SIM/NAO", required = true)
    private TIPOVOTO vote;

    @NotBlank(message = "Campo 'idPauta' não pode ser vazio")
    @NotNull
    @Schema(name = "idPauta", description =  "Identificador da Pauta", required = true)
    private Long idPauta;
}
