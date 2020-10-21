package br.edu.unidep.ApiES.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidep.ApiES.event.ObjetoCriadoEvent;
import br.edu.unidep.ApiES.exceptionhandler.ApiESExceptionHandler.Erro;
import br.edu.unidep.ApiES.exceptionhandler.CategoriaInexistenteOuInativaException;
import br.edu.unidep.ApiES.exceptionhandler.MarcaInexistenteOuInativaException;
import br.edu.unidep.ApiES.model.Produto;
import br.edu.unidep.ApiES.repository.ProdutoRepository;
import br.edu.unidep.ApiES.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository repositorio;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageResource;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Produto> produtos = repositorio.findAll();
		return !produtos.isEmpty() ? ResponseEntity.ok(produtos) :
			ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto, HttpServletResponse reponse) {
		Produto produtoSalvo = produtoService.salvar(produto);
		
		publisher.publishEvent(new ObjetoCriadoEvent(this, reponse, produto.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@ExceptionHandler({ MarcaInexistenteOuInativaException.class })
	private ResponseEntity<Object> MarcaInexistenteOuInativaException(MarcaInexistenteOuInativaException ex){
		String menssagemUsuario = messageResource.getMessage("marca.inexistente-ou-inativo", null, LocaleContextHolder.getLocale());
		String menssagemDev = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(menssagemUsuario, menssagemDev));
		
		return ResponseEntity.badRequest().body(erros);		
	}
	
	@ExceptionHandler({ CategoriaInexistenteOuInativaException.class})
	private ResponseEntity<Object> CategoriaInexistenteOuInativaException(CategoriaInexistenteOuInativaException ex) {
		String menssagemUsuario = messageResource.getMessage("categoria.inexistente-ou-inativo", null, LocaleContextHolder.getLocale());
		String menssagemDev = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(menssagemUsuario, menssagemDev));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	@GetMapping("/{codigo_produto}")
	public ResponseEntity<Produto> buscaPorId(@PathVariable Long codigo_produto){
		Produto produto = repositorio.findOne(codigo_produto);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo_produto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo_produto) {
		repositorio.delete(codigo_produto);
	}
	
	@PutMapping("/{codigo_produto}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigo_produto, @Valid @RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.atualizar(codigo_produto, produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
}
