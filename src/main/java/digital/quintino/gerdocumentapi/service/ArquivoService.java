package digital.quintino.gerdocumentapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.repository.ArquivoImplementacaoService;
import digital.quintino.gerdocumentapi.repository.ArquivoRepository;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Autowired
	private ArquivoImplementacaoService arquivoImplementacaoService;
	
	// TODO -- Validar se o arquivo está duplicado
	public ArquivoDomain uploadOne(MultipartFile multipartFile) {
		ArquivoDomain arquivoDomain = new ArquivoDomain();
		try {
			arquivoDomain = new ArquivoDomain(multipartFile.getOriginalFilename(), this.converterTamanhoArquivo(multipartFile.getSize()), multipartFile.getContentType(), multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.arquivoRepository.save(arquivoDomain);
	}
	
	private String converterTamanhoArquivo(Long tamanho) {
		Long tamanhoArquivo = ((tamanho / 1024) / 1024);
		return String.valueOf(tamanhoArquivo).toString().concat(" MB");
	}
	
	@Transactional
	public ArquivoDomain downloadOne(String codigo) throws Exception {
		return this.arquivoRepository.findByCodigo(codigo);
	}

	@Transactional
	private void verificarArquivoDuplicidade(MultipartFile multipartFile) {
		if(this.arquivoImplementacaoService.isArquivoDuplicidade(multipartFile)) {
			System.out.println("Arquivo Duplicado!");
		}
	}

}
