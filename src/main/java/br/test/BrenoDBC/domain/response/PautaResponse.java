package br.test.BrenoDBC.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resultado da criação de uma nova pauta")
@ToString
public class PautaResponse {

    private Long idPauta;
}
