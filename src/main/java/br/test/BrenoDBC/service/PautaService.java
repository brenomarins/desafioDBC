package br.test.BrenoDBC.service;


import br.test.BrenoDBC.domain.request.IniciaVotacaoPautaRequest;
import br.test.BrenoDBC.domain.request.NovoVotoPautaRequest;
import br.test.BrenoDBC.domain.request.NovaPautaRequest;

public interface PautaService {

    boolean addVoto(NovoVotoPautaRequest novoVotoPautaRequest);

    Long criaPauta(NovaPautaRequest novaPautaRequest);

    Long iniciaSecao(IniciaVotacaoPautaRequest iniciaVotacaoPautaRequest);

    String contabilizaVotos(Long idPauta);
}
