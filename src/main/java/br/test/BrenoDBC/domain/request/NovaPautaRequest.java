package br.test.BrenoDBC.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requisicao para registro de nova pauta")
public class NovaPautaRequest {

    @NotBlank(message = "Campo 'nomePauta' não pode ser vazio")
    @NotNull
    @NotEmpty
    @Schema(name = "nomePauta", description =  "Nome da pauta a ser criada", required = true)
    private String nomePauta;

}
