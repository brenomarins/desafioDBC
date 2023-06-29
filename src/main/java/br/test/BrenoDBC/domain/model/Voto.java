package br.test.BrenoDBC.domain.model;

import br.test.BrenoDBC.domain.enums.TIPOVOTO;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.LocalDateTime;

@Entity
@Table(name = "voto")
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvoto")
    private Long id;

    @Column(name = "voto")
    @Enumerated(EnumType.STRING)
    private TIPOVOTO voto;

    @Column(name = "cpfUsuario")
    private String cpfUsuario;

    @Column(name = "idpauta")
    private Long idpauta;

    @Column(name = "dataVoto")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataVoto;
}
