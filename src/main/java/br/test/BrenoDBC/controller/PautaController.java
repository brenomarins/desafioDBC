package br.test.BrenoDBC.controller;

import br.test.BrenoDBC.domain.request.IniciaVotacaoPautaRequest;
import br.test.BrenoDBC.domain.request.NovoVotoPautaRequest;
import br.test.BrenoDBC.domain.request.NovaPautaRequest;
import br.test.BrenoDBC.domain.response.PautaResponse;
import br.test.BrenoDBC.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;


    @PostMapping("/nova")
    @Operation(description = "Adiciona Pauta")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Pauta criada")
    public PautaResponse createPauta(@RequestBody NovaPautaRequest novaPautaRequest) {
        return new PautaResponse(pautaService.criaPauta(novaPautaRequest));
    }

    @PutMapping("/secao")
    @Operation(description = "Inicia a secao de votacao")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Secao iniciada")
    public void iniciSessao(@RequestBody IniciaVotacaoPautaRequest iniciaVotacaoPautaRequest) {
        pautaService.iniciaSecao(iniciaVotacaoPautaRequest);
    }

    @PostMapping("/voto")
    @Operation(description = "Adiciona Voto")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Voto aceito")
    public void vote(@RequestBody NovoVotoPautaRequest novoVotoPautaRequest) {
        boolean newUserVo = pautaService.addVoto(novoVotoPautaRequest);
    }

    @GetMapping("/{idPauta}/contabiliza")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Resultado votacao")
    public String contabiliza(@PathVariable("idPauta") Long idPauta) {
        return pautaService.contabilizaVotos(idPauta);
    }

}
