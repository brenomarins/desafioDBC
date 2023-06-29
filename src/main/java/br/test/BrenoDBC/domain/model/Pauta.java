package br.test.BrenoDBC.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="pauta")
@Data
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpauta")
    private Long id;

    @Column(name = "nomepauta")
    private String nomePauta;

    @Column(name = "datapauta")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataInicioPauta;

    @Column(name = "tempoativovotacao")
    private Integer tempoAtivoVotacao;
}
