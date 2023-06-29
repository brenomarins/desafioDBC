package br.test.BrenoDBC.reposotory;

import br.test.BrenoDBC.domain.enums.TIPOVOTO;
import br.test.BrenoDBC.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto,Long> {

    @Query("SELECT u FROM Voto u WHERE u.idpauta = ?1 and u.cpfUsuario = ?2")
    Optional<Voto> findVotoByIdPautaAndCpfUsuario(Long idPauta, String cpfUsuario);

    @Query("select  count(voto.voto) as quantidade from Voto voto where voto.idpauta = ?1 and voto.voto = ?2 group by voto.voto")
    Optional<Integer> contarVotos(Long idPauta,TIPOVOTO tipo);

}
