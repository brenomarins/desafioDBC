package br.test.BrenoDBC.domain.enums;

import lombok.Getter;

@Getter
public enum MENSAGEMEXCECAO {

    PAUTA_INEXISTENTE("pauta nao cadastrada"),

    PAUTA_NOME_VAZIA("pauta com nome vazio"),

    PAUTA_ABERTA("Pauta aberta em votação"),

    PAUTA_NAO_INICIADA("pauta não aberta para votaçao"),
    PAUTA_ENCERRADA("Pauta encerrada para votação"),

    VOTO_INVALIDO("Voto Invalido"),
    VOTO_JA_REGISTRADO("Voto ja registrado para o usuario"),

    USUARIO_VAZIO("Usuario Vazio");
    private final String mensagem;

    MENSAGEMEXCECAO(String msg) {
        this.mensagem = msg;
    }

    @Override
    public String toString() {
        return mensagem;
    }
}
