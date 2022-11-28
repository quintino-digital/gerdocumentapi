package digital.quintino.gerdocumentapi.controller;

import digital.quintino.gerdocumentapi.domain.DiretorioDomain;
import digital.quintino.gerdocumentapi.service.DiretorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diretorio")
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

}
