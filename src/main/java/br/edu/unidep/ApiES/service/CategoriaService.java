package br.edu.unidep.ApiES.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.ApiES.model.Categoria;
import br.edu.unidep.ApiES.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = repositorio.findOne(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return repositorio.save(categoriaSalva);
	}
}
