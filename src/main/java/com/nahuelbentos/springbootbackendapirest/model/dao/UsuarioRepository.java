package com.nahuelbentos.springbootbackendapirest.model.dao;

import com.nahuelbentos.springbootbackendapirest.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByUsername( String username);
}
