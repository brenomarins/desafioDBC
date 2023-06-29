package br.test.BrenoDBC.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requisicao para inicio de votação")
public class IniciaVotacaoPautaRequest {

    @NotBlank(message = "Campo 'idPauta' não pode ser vazio")
    @NotNull
    @Schema(name = "idPauta", description =  "Identificador da Pauta", required = true)
    private Long idPauta;

    @Schema(name = "tempoVotacaoSecao", description =  "Tempo da duração da votacao em minutos")
    private Integer tempoVotacaoSecao;

}
