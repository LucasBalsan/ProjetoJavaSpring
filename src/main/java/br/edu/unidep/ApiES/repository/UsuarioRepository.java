package br.edu.unidep.ApiES.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unidep.ApiES.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);   
}
