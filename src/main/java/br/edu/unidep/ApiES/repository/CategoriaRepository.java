package br.edu.unidep.ApiES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.ApiES.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
