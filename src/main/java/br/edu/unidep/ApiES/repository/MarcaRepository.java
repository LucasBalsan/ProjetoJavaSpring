package br.edu.unidep.ApiES.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.ApiES.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
