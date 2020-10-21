package br.edu.unidep.ApiES.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.ApiES.exceptionhandler.PessoaInexistenteOuInativaException;
import br.edu.unidep.ApiES.model.Pessoa;
import br.edu.unidep.ApiES.model.Venda;
import br.edu.unidep.ApiES.repository.PessoaRepository;
import br.edu.unidep.ApiES.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private PessoaRepository pessoaRepositorio;
	
	@Autowired
	private VendaRepository repositorio;
	
	public Venda salvar(Venda venda) {
		
		boolean pessoaInativa = false;
		
		Pessoa pessoa = pessoaRepositorio.findOne(venda.getPessoa().getCodigo());
		
		if (pessoa != null) {
			pessoaInativa = pessoa.isInativo();
		}
		
		if (pessoa == null || pessoaInativa) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return repositorio.save(venda);
	}
	
	
}
