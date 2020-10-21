package br.edu.unidep.ApiES.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.ApiES.model.Fornecedor;
import br.edu.unidep.ApiES.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repositorio;
	
	public Fornecedor atualizar(Long codigo, Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = repositorio.findOne(codigo);
		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "codigo");
		return repositorio.save(fornecedorSalvo);
	}
}
