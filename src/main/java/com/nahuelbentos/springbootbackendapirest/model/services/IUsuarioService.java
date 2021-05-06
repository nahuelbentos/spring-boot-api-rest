package com.nahuelbentos.springbootbackendapirest.model.services;


import com.nahuelbentos.springbootbackendapirest.model.entity.Usuario;

public interface IUsuarioService {

	 Usuario findByUsername(String username);
}
