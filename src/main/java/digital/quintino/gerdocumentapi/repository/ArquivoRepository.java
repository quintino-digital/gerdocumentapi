package digital.quintino.gerdocumentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoDomain, Long> { 
    public ArquivoDomain findByCodigo(String codigo);
}
