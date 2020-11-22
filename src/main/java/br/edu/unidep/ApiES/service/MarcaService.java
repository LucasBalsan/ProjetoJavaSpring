package br.edu.unidep.ApiES.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.ApiES.model.Marca;
import br.edu.unidep.ApiES.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository repositorio;

	public Marca atualizar(Long codigo, Marca marca) {
		
		Marca marcaSalva = repositorio.findOne(codigo);
		
		BeanUtils.copyProperties(marca, marcaSalva, "codigo");
		return repositorio.save(marcaSalva);
	}
}
