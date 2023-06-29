package br.test.BrenoDBC.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum RESULTADO {
    APROVADO("APROVADO"),
    REPROVADO("REPROVADO"),
    EMPATADO("EMPATADO");

    private String status;

    RESULTADO(String msg) {
        this.status = msg;
    }

    @Override
    public String toString() {
        return status;
    }
}
