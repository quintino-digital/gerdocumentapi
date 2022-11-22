package digital.quintino.gerdocumentapi.service;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.repository.ArquivoImplementacaoService;
import digital.quintino.gerdocumentapi.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Autowired
	private ArquivoImplementacaoService arquivoImplementacaoService;
	
	// TODO -- Validar se o arquivo está duplicado
	@Transactional
	public ArquivoDomain uploadOne(MultipartFile multipartFile) throws Exception {
		ArquivoDomain arquivoDomain = new ArquivoDomain();
		if(this.verificarArquivoDuplicidade(multipartFile)) {
			throw new Exception("O mesmo arquivo já foi cadastrado na base de Dados!");
		}
		try {
			arquivoDomain = new ArquivoDomain(multipartFile.getOriginalFilename(), this.retornarTamanhoArquivo(multipartFile.getSize()), multipartFile.getContentType(), multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.arquivoRepository.save(arquivoDomain);
	}
	
	private String retornarTamanhoArquivo(Long tamanho) {
		
		long GB = (((tamanho / 1024) / 1024) / 1024);
		long MB = ((tamanho / 1024) / 1024);
		long KB = (tamanho / 1024);
		
		if(GB != 0L) {
			return String.valueOf(((tamanho / 1024) / 1024)).toString().concat(" GB");
		} 
		if(MB != 0L) {
			return String.valueOf(((tamanho / 1024) / 1024)).toString().concat(" MB");
		}
		if(KB != 0L) {
			return String.valueOf((tamanho / 1024)).toString().concat(" KB");
		} else {
			return String.valueOf(1).toString().concat(" KB");
		}
	}
	
	@Transactional
	public ArquivoDomain downloadOne(String codigo) throws Exception {
		return this.arquivoRepository.findByCodigo(codigo);
	}

	private Boolean verificarArquivoDuplicidade(MultipartFile multipartFile) {
		return this.arquivoImplementacaoService.isArquivoDuplicidade(multipartFile);
	}

	public List<ArquivoDomain> findAll() {
		return this.arquivoRepository.findAll();
	}

}
