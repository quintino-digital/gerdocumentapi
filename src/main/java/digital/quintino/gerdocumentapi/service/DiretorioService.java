package digital.quintino.gerdocumentapi.service;

import digital.quintino.gerdocumentapi.domain.DiretorioDomain;
import digital.quintino.gerdocumentapi.repository.DiretorioImplementacaoService;
import digital.quintino.gerdocumentapi.repository.DiretorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiretorioService {

	@Autowired
	private DiretorioRepository diretorioRepository;

	@Autowired
	private DiretorioImplementacaoService diretorioImplementacaoService;

	public DiretorioDomain findOne(String codigo) {
		return this.diretorioRepository.findByCodigo(codigo);
	}

	@Transactional
	public DiretorioDomain saveOne(DiretorioDomain diretorioDomain) {
		return this.diretorioRepository.save(diretorioDomain);
	}

	public List<DiretorioDomain> findAll() {
		return this.diretorioRepository.findAll();
	}

	public List<DiretorioDomain> recuperarDiretorioRaiz() {
		return this.diretorioImplementacaoService.recuperarDiretorioRaiz();
	}

}
