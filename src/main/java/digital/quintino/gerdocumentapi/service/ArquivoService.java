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
		}
		return null;
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
