package br.test.BrenoDBC.service.impl;

import br.test.BrenoDBC.domain.enums.MENSAGEMEXCECAO;
import br.test.BrenoDBC.domain.enums.RESULTADO;
import br.test.BrenoDBC.domain.enums.TIPOVOTO;
import br.test.BrenoDBC.domain.model.Pauta;
import br.test.BrenoDBC.domain.model.Voto;
import br.test.BrenoDBC.domain.request.IniciaVotacaoPautaRequest;
import br.test.BrenoDBC.domain.request.NovoVotoPautaRequest;
import br.test.BrenoDBC.domain.request.NovaPautaRequest;
import br.test.BrenoDBC.reposotory.PautaRepository;
import br.test.BrenoDBC.reposotory.VotoRepository;
import br.test.BrenoDBC.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public boolean addVoto(NovoVotoPautaRequest novoVotoPautaRequest) {
        Optional<Pauta> pauta = pautaRepository.findById(novoVotoPautaRequest.getIdPauta());
        if(pauta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEMEXCECAO.PAUTA_INEXISTENTE.getMensagem());
        }
        if((pauta.get().getTempoAtivoVotacao() == null) || pauta.get().getDataInicioPauta() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, MENSAGEMEXCECAO.PAUTA_NAO_INICIADA.getMensagem());
        }
        Optional<Voto> jaVotou = votoRepository.findVotoByIdPautaAndCpfUsuario(novoVotoPautaRequest.getIdPauta(), novoVotoPautaRequest.getCpf());
        if(jaVotou.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MENSAGEMEXCECAO.VOTO_JA_REGISTRADO.getMensagem());
        }
        if(isPautaEncerrada(pauta.get())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, MENSAGEMEXCECAO.PAUTA_ENCERRADA.getMensagem());
        }
        Voto voto = new Voto();
        voto.setIdpauta(novoVotoPautaRequest.getIdPauta());
        voto.setVoto(novoVotoPautaRequest.getVote());
        voto.setCpfUsuario(novoVotoPautaRequest.getCpf());
        voto.setDataVoto(LocalDateTime.now());
        votoRepository.save(voto);
    return true;
    }

    @Override
    public Long criaPauta(NovaPautaRequest pautaVO) {
        if(pautaVO.getNomePauta().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MENSAGEMEXCECAO.PAUTA_NOME_VAZIA.getMensagem());
        }
        Pauta pauta = new Pauta();
        pauta.setNomePauta(pautaVO.getNomePauta());
        return pautaRepository.save(pauta).getId();
    }

    @Override
    public Long iniciaSecao(IniciaVotacaoPautaRequest iniciaVotacaoPautaRequest) {
        Optional<Pauta> pauta = pautaRepository.findById(iniciaVotacaoPautaRequest.getIdPauta());
        if(pauta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEMEXCECAO.PAUTA_INEXISTENTE.getMensagem());
        }
        if(pauta.get().getDataInicioPauta() != null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, MENSAGEMEXCECAO.PAUTA_ABERTA.getMensagem());
        }
        if(pauta.isPresent()){
            if((iniciaVotacaoPautaRequest.getTempoVotacaoSecao() == null) || (iniciaVotacaoPautaRequest.getTempoVotacaoSecao() <= 1)){
                pauta.get().setTempoAtivoVotacao(1);
            }else{
                pauta.get().setTempoAtivoVotacao(iniciaVotacaoPautaRequest.getTempoVotacaoSecao());
            }
            pauta.get().setDataInicioPauta(LocalDateTime.now());
            pautaRepository.save(pauta.get());
        }
        return pauta.get().getId();
    }

    @Override
    public String contabilizaVotos(Long idPauta) {
        Optional<Pauta> pauta = pautaRepository.findById(idPauta);
        if(pauta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEMEXCECAO.PAUTA_INEXISTENTE.getMensagem());
        }
        if((pauta.get().getTempoAtivoVotacao() == null) || pauta.get().getDataInicioPauta() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, MENSAGEMEXCECAO.PAUTA_NAO_INICIADA.getMensagem());
        }
        if(!isPautaEncerrada(pauta.get())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, MENSAGEMEXCECAO.PAUTA_ABERTA.getMensagem());
        }
        Optional<Integer> resultadosim = votoRepository.contarVotos(idPauta,TIPOVOTO.SIM);
        Optional<Integer> resultadoNao = votoRepository.contarVotos(idPauta,TIPOVOTO.NAO);

        if(resultadosim.isPresent() && resultadoNao.isPresent()){
            if(resultadosim.get() > resultadoNao.get()){
                return RESULTADO.APROVADO.name();
            }
            if(resultadosim.get() < resultadoNao.get()){
                return RESULTADO.REPROVADO.name();
            }
            return RESULTADO.EMPATADO.name();
        }
        if(resultadosim.isPresent() && resultadoNao.isEmpty()){
           return RESULTADO.APROVADO.name();
        }
        if(resultadosim.isEmpty() && resultadoNao.isPresent()){
            return RESULTADO.REPROVADO.name();
        }
        return RESULTADO.EMPATADO.name();
    }

    private boolean isPautaEncerrada(Pauta pauta){
        if(pauta.getDataInicioPauta().plusMinutes(pauta.getTempoAtivoVotacao()).isAfter(LocalDateTime.now())){
            return false;
        }
        return true;
    }


}
