package digital.quintino.gerdocumentapi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;

@Repository
public class ArquivoImplementacaoService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Boolean isArquivoDuplicidade(MultipartFile multipartFile) {
		StringBuilder query = new StringBuilder("SELECT arquivoDomain.nome, arquivoDomain.extencao ")
				.append("FROM ArquivoDomain arquivoDomain ")
				.append("WHERE arquivoDomain.nome = :nomeParameter ")
				.append("AND arquivoDomain.extencao = :extencaoParameter ");
		TypedQuery<ArquivoDomain> typeQuery = this.entityManager.createQuery(query.toString(), ArquivoDomain.class);
			typeQuery.setParameter("nomeParameter", multipartFile.getOriginalFilename());
			typeQuery.setParameter("extencaoParameter", multipartFile.getContentType());
		return typeQuery.getSingleResult() == null ? false : true;
	}

}
