package br.edu.unidep.ApiES.service;

import java.util.Date;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unidep.ApiES.exceptionhandler.DataDeCompraIncorretaException;
import br.edu.unidep.ApiES.exceptionhandler.FornecedorInexistenteOuInativoException;
import br.edu.unidep.ApiES.exceptionhandler.ProdutoInexistenteOuInativaException;
import br.edu.unidep.ApiES.exceptionhandler.ValidadeLoteIncorretaException;
import br.edu.unidep.ApiES.model.CompraProdutos;
import br.edu.unidep.ApiES.model.Fornecedor;
import br.edu.unidep.ApiES.model.Produto;
import br.edu.unidep.ApiES.repository.CompraProdutoRepository;
import br.edu.unidep.ApiES.repository.FornecedorRepository;
import br.edu.unidep.ApiES.repository.ProdutoRepository;


@Service
public class CompraProdutosService {

	@Autowired
	private CompraProdutoRepository compraRepositorio;
	
	@Autowired
	private ProdutoRepository produtoRepositorio;
	
	@Autowired
	private FornecedorRepository fornecedorRepositorio;
	
	public CompraProdutos atualizar(Long codigo, CompraProdutos compraProdutos) {
		Date dataAtual = new Date();
		
		CompraProdutos compraProdutosSalvo = compraRepositorio.findOne(codigo);
		Produto produto =  produtoRepositorio.findOne(compraProdutos.getProduto().getCodigo());
		Fornecedor fornecedor = fornecedorRepositorio.findOne(compraProdutos.getFornecedor().getCodigo()); 
		
		//if (produto != null) {
		//	throw new ProdutoInexistenteOuInativaException();
		//} else if (fornecedor != null) {
		//	throw new FornecedorInexistenteOuInativoException();
		//} else if (dataAtual.compareTo(compraProdutos.getValidadeLote()) > 0) {
		//	throw new ValidadeLoteIncorretaException();
		//} else if (dataAtual.compareTo(compraProdutos.getDataCompra()) != 0) {
		//	throw new DataDeCompraIncorretaException();
		//} 
		
		BeanUtils.copyProperties(compraProdutos, compraProdutosSalvo, "codigo");
		return compraRepositorio.save(compraProdutosSalvo);
	}

	public CompraProdutos salvar(CompraProdutos compraProdutos) {				
		Date dataAtual = new Date();
		
		System.out.println(dataAtual);
		System.out.println(compraProdutos.getValidadeLote());
		
		Produto produto =  produtoRepositorio.findOne(compraProdutos.getProduto().getCodigo());
		Fornecedor fornecedor = fornecedorRepositorio.findOne(compraProdutos.getFornecedor().getCodigo()); 
		
		//if (produto != null) {
		//	throw new ProdutoInexistenteOuInativaException();
	///	} else if (fornecedor != null) {
		//	throw new FornecedorInexistenteOuInativoException();
		//} else if (dataAtual.compareTo(compraProdutos.getValidadeLote()) > 0) {
		//	throw new ValidadeLoteIncorretaException();
		//} else if (dataAtual.compareTo(compraProdutos.getDataCompra()) != 0) {
		//	throw new DataDeCompraIncorretaException();
	//	} 
	
		return compraRepositorio.save(compraProdutos);
	}

}
