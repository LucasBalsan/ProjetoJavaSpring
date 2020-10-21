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
import br.edu.unidep.ApiES.model.Categoria;
import br.edu.unidep.ApiES.repository.CategoriaRepository;
import br.edu.unidep.ApiES.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaRepository repositorio;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Categoria> categorias = repositorio.findAll();
		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) :
			ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
		Categoria categoriaSalva = repositorio.save(categoria);
		
		publisher.publishEvent(new ObjetoCriadoEvent(this, response, categoria.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo_categoria}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo_categoria) {
		Categoria categoria = repositorio.findOne(codigo_categoria);
		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo_categoria}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo_categoria) {
		repositorio.delete(codigo_categoria);
	}
	
	@PutMapping("/{codigo_categoria}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo_categoria, @Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.atualizar(codigo_categoria, categoria);
		return ResponseEntity.ok(categoriaSalva);				
	}
	
	
}
