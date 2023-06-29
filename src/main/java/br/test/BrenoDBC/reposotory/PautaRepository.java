package br.test.BrenoDBC.reposotory;

import br.test.BrenoDBC.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
