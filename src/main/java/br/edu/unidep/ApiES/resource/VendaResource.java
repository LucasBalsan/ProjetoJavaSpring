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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unidep.ApiES.event.ObjetoCriadoEvent;
import br.edu.unidep.ApiES.exceptionhandler.ApiESExceptionHandler.Erro;
import br.edu.unidep.ApiES.exceptionhandler.PessoaInexistenteOuInativaException;
import br.edu.unidep.ApiES.model.Venda;
import br.edu.unidep.ApiES.repository.VendaRepository;
import br.edu.unidep.ApiES.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService vendaServico;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private VendaRepository repositorio;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Venda> vendas = repositorio.findAll();
		return !vendas.isEmpty() ? ResponseEntity.ok(vendas) :
			ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Venda> criar(@Valid @RequestBody Venda venda, HttpServletResponse response) {
		
		Venda vendaSalva = vendaServico.salvar(venda);
		
		publisher.publishEvent(new ObjetoCriadoEvent(this, response, venda.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
		
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, 
				LocaleContextHolder.getLocale());
		
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}

}
