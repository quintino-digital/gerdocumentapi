package digital.quintino.gerdocumentapi.controller;

import digital.quintino.gerdocumentapi.domain.DiretorioDomain;
import digital.quintino.gerdocumentapi.dto.DiretorioResponseDTO;
import digital.quintino.gerdocumentapi.service.DiretorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diretorio")
@CrossOrigin("*")
public class DiretorioController {
	
	@Autowired
	private DiretorioService diretorioService;

	@PostMapping
	public ResponseEntity<DiretorioDomain> saveOne(@RequestBody DiretorioDomain diretorioDomain) {
		return ResponseEntity.ok().body(this.diretorioService.saveOne(diretorioDomain));
	}

	@GetMapping
	public ResponseEntity<List<DiretorioDomain>> findAll() {
		return ResponseEntity.ok(this.diretorioService.findAll());
	}

	@GetMapping("/raiz")
	public ResponseEntity<List<DiretorioDomain>> recuperarDiretorioRaiz() {
		return ResponseEntity.ok().body(this.diretorioService.recuperarDiretorioRaiz());
	}

	@GetMapping("{codigo}")
	public ResponseEntity<DiretorioDomain> findOne(@PathVariable("codigo") String codigo) {
		return ResponseEntity.ok().body(this.diretorioService.findOne(codigo));
	}

	@GetMapping("/subdiretorio/{codigo}")
	public ResponseEntity<List<DiretorioDomain>> recuperarSubDiretorio(@PathVariable("codigo") String codigo) {
		return ResponseEntity.ok().body(this.diretorioService.recuperarSubDiretorio(codigo));
	}

	@GetMapping("/primeiro-nivel")
	public ResponseEntity<List<DiretorioResponseDTO>> recuperarDiretorioPrimeiroNivel() {
		return ResponseEntity.ok().body(this.diretorioService.recuperarDiretorioPrimeiroNivel());
	}

	@GetMapping("/segundo-nivel")
	public ResponseEntity<List<DiretorioResponseDTO>> recuperarDiretorioSegundoNivel() {
		return ResponseEntity.ok().body(this.diretorioService.recuperarDiretorioSegundoNivel());
	}

}
