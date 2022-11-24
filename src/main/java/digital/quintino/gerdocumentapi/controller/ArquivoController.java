package digital.quintino.gerdocumentapi.controller;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.dto.ArquivoResponseDTO;
import digital.quintino.gerdocumentapi.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/v1/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;

	@Value("${servidor.ip}")
	private String IP_CONFIGURACAO;

	// TODO -- Implementar Processamento Assincrono para arquivos com tamanho acima de 512MB
	@PostMapping("/all")
	public ResponseEntity<List<ArquivoResponseDTO>> uploadAll(@RequestParam("arquivoList") List<MultipartFile> multipartFileList) throws IOException {
		return ResponseEntity.ok().body(this.arquivoService.uploadAll(multipartFileList));
	}

	@PostMapping
	public ArquivoResponseDTO uploadOne(@RequestParam("arquivo") MultipartFile multipartFile, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		ArquivoDomain arquivoDomain = this.arquivoService.uploadOne(multipartFile);
		return new ArquivoResponseDTO(arquivoDomain.getCodigo(), arquivoDomain.getNome(), arquivoDomain.getTamanho(), arquivoDomain.getExtencao(), this.configurarURLDownload(arquivoDomain.getCodigo(), httpServletRequest, httpServletResponse));
	}
	
	private String configurarURLDownload(String codigo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnknownHostException, MalformedURLException {
		StringBuilder url = new StringBuilder("http://")
				.append(InetAddress.getLocalHost().getHostAddress().equals("127.0.1.1") ? IP_CONFIGURACAO : InetAddress.getLocalHost().getHostAddress())
				.append(":")
				.append(httpServletRequest.getServerPort())
				.append("/api/v1/arquivo/")
				.append(codigo);
		System.out.println(url);
		return url.toString();
	}

	// TODO -- Salvar URL no Banco de Dados
	@GetMapping("{codigo}")
	public ResponseEntity<Resource> downloadOne(@PathVariable String codigo) throws Exception {
		ArquivoDomain arquivoDomain = this.arquivoService.downloadOne(codigo);
		return ResponseEntity
				.ok()
					.contentType(MediaType.parseMediaType(arquivoDomain.getExtencao()))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivoDomain.getNome() + "\"")
							.body(new ByteArrayResource(arquivoDomain.getConteudo()));
	}

	@GetMapping
	public ResponseEntity<List<ArquivoResponseDTO>> findAll() {
		return ResponseEntity.ok(this.arquivoService.findAll());
	}

	@GetMapping("/storage/{codigo}")
	public ResponseEntity<Resource> downloadOneStorage(@PathVariable String codigo) throws MalformedURLException {
		ArquivoResponseDTO arquivoResponseDTO = this.arquivoService.downloadOneStorage(codigo);
		Resource resource = new UrlResource(arquivoResponseDTO.getUrl());
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(arquivoResponseDTO.getExtencao()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivoResponseDTO.getNome() + "\"")
				.body(resource);
	}

}
