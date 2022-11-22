package digital.quintino.gerdocumentapi.controller;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.dto.ArquivoResponseDTO;
import digital.quintino.gerdocumentapi.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;

	// TODO -- Implementar Processamento Assincrono para arquivos com tamanho acima de 512MB
	@PostMapping
	public ArquivoResponseDTO uploadOne(@RequestParam("arquivo") MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws Exception {
		ArquivoDomain arquivoDomain = this.arquivoService.uploadOne(multipartFile);
		return new ArquivoResponseDTO(arquivoDomain.getCodigo(), arquivoDomain.getNome(), arquivoDomain.getTamanho(), arquivoDomain.getExtencao(), this.configurarURLDownload(arquivoDomain.getCodigo(), httpServletRequest));
	}
	
	private String configurarURLDownload(String codigo, HttpServletRequest httpServletRequest) throws UnknownHostException, MalformedURLException {
		StringBuilder url = new StringBuilder("http://")
				.append(InetAddress.getLocalHost().getHostAddress())
				.append(":")
				.append(httpServletRequest.getServerPort())
				.append("/api/v1/arquivo/")
				.append(codigo);
		System.out.println(url);
		return url.toString();
	}

	@GetMapping("{codigo}")
	public ResponseEntity<Resource> downloadOne(@PathVariable String codigo) throws Exception {
		ArquivoDomain arquivoDomain = this.arquivoService.downloadOne(codigo);
		return ResponseEntity
				.ok()
					.contentType(MediaType.parseMediaType(arquivoDomain.getExtencao()))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivoDomain.getNome() + "\"")
							.body(new ByteArrayResource(arquivoDomain.getConteudo()));
	}

	// TODO -- Retornar DTO com apenas dados básicos dos Arquivos
	@GetMapping
	public ResponseEntity<List<ArquivoDomain>> findAll() {
		return ResponseEntity.ok(this.arquivoService.findAll());
	}

}
