package br.edu.unidep.ApiES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.ApiES.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
