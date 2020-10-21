package br.edu.unidep.ApiES.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidep.ApiES.event.ObjetoCriadoEvent;
import br.edu.unidep.ApiES.model.Fornecedor;
import br.edu.unidep.ApiES.repository.FornecedorRepository;
import br.edu.unidep.ApiES.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository repositorio;
	
	@Autowired 
	private FornecedorService fornecedorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Fornecedor> fornecedores = repositorio.findAll();
		return !fornecedores.isEmpty() ? ResponseEntity.ok(fornecedores) :
			ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> salvar(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response) {
		Fornecedor fornecedorSalvo = repositorio.save(fornecedor);
		
		publisher.publishEvent(new ObjetoCriadoEvent(this, response, fornecedor.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}
	
	@GetMapping("/{codigo_fornecedor}")
	public ResponseEntity<Fornecedor> buscarPeloCodigo(@PathVariable Long codigo_fornecedor) {
		Fornecedor fornecedor = repositorio.findOne(codigo_fornecedor);
		if (fornecedor != null) {
			return ResponseEntity.ok(fornecedor);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo_fornecedor}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo_fornecedor) {
		repositorio.delete(codigo_fornecedor);
	}

	@PutMapping("/{codigo_fornecedor}")
	public ResponseEntity<Fornecedor> atualizar(@PathVariable Long codigo_fornecedor, @Valid @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorService.atualizar(codigo_fornecedor, fornecedor);
		return ResponseEntity.ok(fornecedorSalvo);
	}
}
