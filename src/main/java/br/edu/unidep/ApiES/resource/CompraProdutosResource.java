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
import br.edu.unidep.ApiES.exceptionhandler.DataDeCompraIncorretaException;
import br.edu.unidep.ApiES.exceptionhandler.FornecedorInexistenteOuInativoException;
import br.edu.unidep.ApiES.exceptionhandler.ProdutoInexistenteOuInativaException;
import br.edu.unidep.ApiES.exceptionhandler.ValidadeLoteIncorretaException;
import br.edu.unidep.ApiES.model.CompraProdutos;
import br.edu.unidep.ApiES.repository.CompraProdutoRepository;
import br.edu.unidep.ApiES.service.CompraProdutosService;

@RestController
@RequestMapping("/compra_produtos")
public class CompraProdutosResource {
	
	@Autowired
	private CompraProdutoRepository repositorio;
	
	@Autowired
	private CompraProdutosService compraProdutosService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public ResponseEntity<?> buscar() {
		List<CompraProdutos> compraProdutos = repositorio.findAll();
		return !compraProdutos.isEmpty() ? ResponseEntity.ok(compraProdutos) :
			ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CompraProdutos> salvar(@Valid @RequestBody CompraProdutos compraProduto, HttpServletResponse response) {
		CompraProdutos compraProdutoSalva = compraProdutosService.salvar(compraProduto);
		
		publisher.publishEvent(new ObjetoCriadoEvent(this, response, compraProduto.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(compraProdutoSalva);
	}
	
	@ExceptionHandler({ ProdutoInexistenteOuInativaException.class })
	public ResponseEntity<Object> ProdutoInexistenteOuInativaException(ProdutoInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("produto.inexistente-ou-inativo", null, LocaleContextHolder.getLocale());
		
		String mensagemDev = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ FornecedorInexistenteOuInativoException.class })
	public ResponseEntity<Object> FornecedorInexistenteOuInativoException(FornecedorInexistenteOuInativoException ex) {
		String mensagemUsuario = messageSource.getMessage("fornecedor.inexistente-ou-inativo", null, LocaleContextHolder.getLocale());
		
		String mensagemDev = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ ValidadeLoteIncorretaException.class })
	public ResponseEntity<Object> ValidadeLoteIncorretaException(ValidadeLoteIncorretaException ex) {
		String mensagemUsuario = messageSource.getMessage("compraproduto.validade-lote-incorreta", null, LocaleContextHolder.getLocale());
		
		String mensagemDev = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ DataDeCompraIncorretaException.class })
	public ResponseEntity<Object> DataDeCompraIncorretaException(DataDeCompraIncorretaException ex) {
		String mensagemUsuario = messageSource.getMessage("compraproduto.data-compra-incorreta", null, LocaleContextHolder.getLocale());
		
		String mensagemDev = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDev));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	@GetMapping("/{codigo_compras_produtos}")
	public ResponseEntity<CompraProdutos> buscarPeloCodigo(@PathVariable Long codigo_compras_produtos){
		CompraProdutos compraProdutos = repositorio.findOne(codigo_compras_produtos);
		if (compraProdutos != null) {
			return ResponseEntity.ok(compraProdutos);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo_compras_produtos}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo_compras_produtos) {
		repositorio.delete(codigo_compras_produtos);
	}
	
	@PutMapping("/{codigo_compras_produtos}")
	public ResponseEntity<CompraProdutos> alterar(@PathVariable Long codigo_compras_produtos, @Valid @RequestBody CompraProdutos compraProduto) {
		CompraProdutos compraProdutosSalvo = compraProdutosService.atualizar(codigo_compras_produtos, compraProduto);
		return ResponseEntity.ok(compraProdutosSalvo);
	}
		
}
