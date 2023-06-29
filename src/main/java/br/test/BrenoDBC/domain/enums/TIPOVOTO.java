package br.test.BrenoDBC.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TIPOVOTO {
    SIM("sim"),
    NAO("nao");

    private String tipo;

    private String status;

    TIPOVOTO(String msg) {
        this.status = msg;
    }

    @Override
    public String toString() {
        return status;
    }
}
