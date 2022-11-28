package digital.quintino.gerdocumentapi.service;

import digital.quintino.gerdocumentapi.domain.DiretorioDomain;
import digital.quintino.gerdocumentapi.dto.DiretorioResponseDTO;
import digital.quintino.gerdocumentapi.repository.DiretorioImplementacaoService;
import digital.quintino.gerdocumentapi.repository.DiretorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
		this.recuperarDiretorioPrimeiroNivel();
		return this.diretorioRepository.findAll();
	}

	public List<DiretorioDomain> recuperarDiretorioRaiz() {
		return this.diretorioImplementacaoService.recuperarDiretorioRaiz();
	}

	public List<DiretorioDomain> recuperarSubDiretorio(String codigo) {
		return this.diretorioImplementacaoService.recuperarSubDiretorio(codigo);
	}

	public List<DiretorioResponseDTO> recuperarDiretorioPrimeiroNivel() {
		List<DiretorioResponseDTO> diretorioResponseDTOList = new ArrayList<>();
		for(DiretorioDomain diretorioRaiz : this.recuperarDiretorioRaiz()) {
			DiretorioResponseDTO diretorioResponseDTO = new DiretorioResponseDTO();
			diretorioResponseDTO.setCodigo(diretorioRaiz.getCodigo());
			diretorioResponseDTO.setNome(diretorioRaiz.getNome());
			diretorioResponseDTO.setCodigoDiretorioPai(diretorioRaiz.getCodigoDiretorioPai());
			diretorioResponseDTOList.add(diretorioResponseDTO);
		}
		for(DiretorioResponseDTO diretorioResponseDTO : diretorioResponseDTOList) {
			for(DiretorioDomain diretorioDomainResultado : this.recuperarSubDiretorio(diretorioResponseDTO.getCodigo())) {
				diretorioResponseDTO.getSubdiretorioList().add(diretorioDomainResultado);
			}
		}
		return diretorioResponseDTOList;
	}

}
