package digital.quintino.gerdocumentapi.repository;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ArquivoImplementacaoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Boolean isArquivoDuplicidade(MultipartFile multipartFile) {
		StringBuilder query = new StringBuilder("SELECT arquivoDomain ")
				.append("FROM ArquivoDomain arquivoDomain ")
				.append("WHERE arquivoDomain.nome LIKE :nomeParameter ")
				.append("AND arquivoDomain.extencao LIKE :extencaoParameter ");
		TypedQuery<ArquivoDomain> typeQuery = this.entityManager.createQuery(query.toString(), ArquivoDomain.class);
			typeQuery.setParameter("nomeParameter", multipartFile.getOriginalFilename());
			typeQuery.setParameter("extencaoParameter", multipartFile.getContentType());
		List<ArquivoDomain> arquivoDomainsList = typeQuery.getResultList();
		return arquivoDomainsList.size() == 0 ? false : true;
	}

	public List<ArquivoDomain> recuperarArquivosDiretorio(String codigoDiretorio) {
		StringBuilder query = new StringBuilder("SELECT arquivoDomain ")
				.append("FROM ArquivoDomain arquivoDomain ")
				.append("JOIN DiretorioDomain diretorioDomain ON diretorioDomain.codigo = arquivoDomain.diretorioDomain.codigo ")
				.append("WHERE arquivoDomain.diretorioDomain.codigo = :id_diretorio_parameter ");
		TypedQuery<ArquivoDomain> typeQuery = this.entityManager.createQuery(query.toString(), ArquivoDomain.class);
			typeQuery.setParameter("id_diretorio_parameter", codigoDiretorio);
		return typeQuery.getResultList();
	}

}
