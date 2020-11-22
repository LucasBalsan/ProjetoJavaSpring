package br.edu.unidep.ApiES.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import br.edu.unidep.ApiES.model.Marca;
import br.edu.unidep.ApiES.repository.MarcaRepository;
import br.edu.unidep.ApiES.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaResource {
	
	@Autowired
	private MarcaRepository repositorio;
	
	@Autowired
	private MarcaService marcaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/ok")
	public String ok() {
		return "OK";
	}
	
	@GetMapping("/ola")
	public String ola() {
		return "ola";
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MARCA') and #oauth2.hasScope('read')")
	public ResponseEntity<?> listar() {
		List<Marca> marcas = repositorio.findAll();
		return !marcas.isEmpty() ? ResponseEntity.ok(marcas) :
			ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MARCA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Marca> salvar(@Valid @RequestBody Marca marca,
			HttpServletResponse response) {
		Marca marcaSalva = repositorio.save(marca);
		
		publisher.publishEvent(new ObjetoCriadoEvent(this, response, marcaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaSalva);
		
	}

	@GetMapping("/{codigo_marca}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MARCA') and #oauth2.hasScope('read')")
	public ResponseEntity<Marca> buscarPeloCodigo(
		   @PathVariable Long codigo_marca) {
		Marca marca = repositorio.findOne(codigo_marca);
		if ( marca != null) { 
			return ResponseEntity.ok(marca);
		} 
		return ResponseEntity.notFound().build();
	
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MARCA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		repositorio.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MARCA') and #oauth2.hasScope('write')")
	public ResponseEntity<Marca> atualizar(@PathVariable Long codigo,
			@Valid @RequestBody Marca marca) {
		
		Marca marcaSalva = marcaService.atualizar(codigo, marca);
		return ResponseEntity.ok(marcaSalva);
	}
	
	
}
