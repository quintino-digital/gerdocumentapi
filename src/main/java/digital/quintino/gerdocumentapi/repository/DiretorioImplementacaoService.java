package digital.quintino.gerdocumentapi.repository;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.domain.DiretorioDomain;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DiretorioImplementacaoService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<DiretorioDomain> recuperarDiretorioRaiz() {
		StringBuilder query = new StringBuilder("SELECT diretorioDomain ")
				.append("FROM DiretorioDomain diretorioDomain ")
				.append("WHERE diretorioDomain.codigoDiretorioPai = ''");
		TypedQuery<DiretorioDomain> typeQuery = this.entityManager.createQuery(query.toString(), DiretorioDomain.class);
		return typeQuery.getResultList();
	}

}
