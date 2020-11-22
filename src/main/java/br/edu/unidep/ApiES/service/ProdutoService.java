package br.edu.unidep.ApiES.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import br.edu.unidep.ApiES.exceptionhandler.CategoriaInexistenteOuInativaException;
//import br.edu.unidep.ApiES.exceptionhandler.MarcaInexistenteOuInativaException;
//import br.edu.unidep.ApiES.model.Categoria;
//import br.edu.unidep.ApiES.model.Marca;
import br.edu.unidep.ApiES.model.Produto;
//import br.edu.unidep.ApiES.repository.CategoriaRepository;
//import br.edu.unidep.ApiES.repository.MarcaRepository;
import br.edu.unidep.ApiES.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepositorio;
	
	//@Autowired
	//private MarcaRepository marcaRepositorio;
	
	//@Autowired
	//private CategoriaRepository categoriaRepositorio;
	
	public Produto atualizar(Long codigo, Produto produto) {
		Produto produtoSalvo = produtoRepositorio.findOne(codigo);
		//Marca marca = marcaRepositorio.findOne(produto.getMarca().getCodigo());			
		//Categoria categoria = categoriaRepositorio.findOne(produto.getCategoria().getCodigo());
			
		//if (marca != null) {
		//	throw new MarcaInexistenteOuInativaException();
		//} else if (categoria != null) {
		//	throw new CategoriaInexistenteOuInativaException();
		//}
		
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
		return produtoRepositorio.save(produtoSalvo);
	}
	
	public Produto salvar(Produto produto) {				
		//Marca marca = marcaRepositorio.findOne(produto.getMarca().getCodigo());			
		//Categoria categoria = categoriaRepositorio.findOne(produto.getCategoria().getCodigo());
			
		//if (marca != null) {
		//	throw new MarcaInexistenteOuInativaException();
		//} else if (categoria != null) {
		//	throw new CategoriaInexistenteOuInativaException();
		//}			
					
		return produtoRepositorio.save(produto);
	}
}
