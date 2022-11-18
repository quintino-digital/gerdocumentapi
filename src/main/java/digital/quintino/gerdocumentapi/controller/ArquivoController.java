package digital.quintino.gerdocumentapi.controller;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import digital.quintino.gerdocumentapi.domain.ArquivoDomain;
import digital.quintino.gerdocumentapi.dto.ArquivoResponseDTO;
import digital.quintino.gerdocumentapi.service.ArquivoService;

@RestController
@RequestMapping("/api/v1/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;
	
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

}
