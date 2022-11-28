package digital.quintino.gerdocumentapi.repository;

import digital.quintino.gerdocumentapi.domain.DiretorioDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretorioRepository extends JpaRepository<DiretorioDomain, Long> {
    public DiretorioDomain findByCodigo(String codigo);
}
