package digital.quintino.gerdocumentapi.service;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.dto.ArquivoResponseDTO;
import digital.quintino.gerdocumentapi.repository.ArquivoImplementacaoService;
import digital.quintino.gerdocumentapi.repository.ArquivoRepository;
import digital.quintino.gerdocumentapi.utility.ConstanteUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class ArquivoService {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Autowired
	private ArquivoImplementacaoService arquivoImplementacaoService;

	@Value("${servidor.ip}")
	private String IP_CONFIGURACAO;

	@Value("${server.port}")
	private String SERVER_PORT_CONFIGURACAO;

	private static String NOME_ARQUIVO;

	public static final String DIRETORIO_UPLOAD = System.getProperty("user.home").concat(ConstanteUtility.REPOSITORIO_UPLOAD);

	public static final Long TAMANHO_MAXIMO_ARQUIVO = 536870912L;
	
	@Transactional
	public ArquivoDomain uploadOne(MultipartFile multipartFile) throws Exception {
		ArquivoDomain arquivoDomain = new ArquivoDomain();
		if(this.verificarArquivoDuplicidade(multipartFile)) {
			throw new Exception("O mesmo arquivo já foi cadastrado na base de Dados!");
		}
		try {
			arquivoDomain = new ArquivoDomain(recuperarNomeArquivo(multipartFile), this.retornarTamanhoArquivo(multipartFile.getSize()), multipartFile.getContentType(), multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.arquivoRepository.save(arquivoDomain);
	}

	private static String recuperarNomeArquivo(MultipartFile multipartFile) {
		NOME_ARQUIVO = multipartFile.getOriginalFilename();
		return NOME_ARQUIVO;
	}

	public List<ArquivoResponseDTO> uploadAll(List<MultipartFile> multipartFileList) throws IOException {
		List<ArquivoResponseDTO> arquivoResponseDTOList = new ArrayList<>();
		for(MultipartFile multipartFile : multipartFileList) {
			Path path = recuperarDiretorioArquivo(multipartFile.getOriginalFilename());
			copy(multipartFile.getInputStream(), path, REPLACE_EXISTING);
			ArquivoDomain arquivoDomain = this.arquivoRepository.save(this.configurarArquivoDomain(multipartFile));
			arquivoResponseDTOList.add(this.configurarArquivoResponseDTO(arquivoDomain));
		}
		return arquivoResponseDTOList;
	}

	private static Path recuperarDiretorioArquivo(String nomeArquivo) {
		return get(DIRETORIO_UPLOAD, nomeArquivo).toAbsolutePath().normalize();
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

	public List<ArquivoResponseDTO> findAll() {
		List<ArquivoResponseDTO> arquivoResponseDTOList = new ArrayList<ArquivoResponseDTO>();
		for(ArquivoDomain arquivoDomain : this.arquivoRepository.findAll()) {
			arquivoResponseDTOList.add(this.configurarArquivoResponseDTO(arquivoDomain));
		}
		return arquivoResponseDTOList;
	}

	private ArquivoDomain configurarArquivoDomain(MultipartFile multipartFile) throws IOException {
		if(multipartFile.getSize() > TAMANHO_MAXIMO_ARQUIVO) {
			return new ArquivoDomain(multipartFile.getOriginalFilename(), this.retornarTamanhoArquivo(multipartFile.getSize()), multipartFile.getContentType(), recuperarDiretorioArquivo(multipartFile.getOriginalFilename()).toString());
		}
		return new ArquivoDomain(multipartFile.getOriginalFilename(), this.retornarTamanhoArquivo(multipartFile.getSize()), multipartFile.getContentType(), multipartFile.getBytes());
	}

	private ArquivoResponseDTO configurarArquivoResponseDTO(ArquivoDomain arquivoDomain) {
		return new ArquivoResponseDTO(arquivoDomain.getCodigo(), arquivoDomain.getNome(), arquivoDomain.getTamanho(), arquivoDomain.getExtencao(), this.configurarURL(arquivoDomain.getCodigo()));
	}

	private String configurarURL(String codigo) {
		StringBuilder url = null;
		try {
			url = new StringBuilder("http://")
					.append(InetAddress.getLocalHost().getHostAddress().equals("127.0.1.1") ? IP_CONFIGURACAO : InetAddress.getLocalHost().getHostAddress())
					.append(":")
					.append(SERVER_PORT_CONFIGURACAO)
					.append("/api/v1/arquivo/")
					.append(codigo);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		return url.toString();
	}

    public ArquivoResponseDTO downloadOneStorage(String codigo) {
		ArquivoDomain arquivoDomain = this.arquivoRepository.findByCodigo(codigo);
		return this.configurarArquivoResponseDTO(arquivoDomain);
    }

}
