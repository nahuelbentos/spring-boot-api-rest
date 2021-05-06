package com.nahuelbentos.springbootbackendapirest.model.services;

import com.nahuelbentos.springbootbackendapirest.model.dao.UsuarioRepository;
import com.nahuelbentos.springbootbackendapirest.model.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger( UsuarioService.class );

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if( usuario == null){
            logger.error(String.format("Error en el login: no existe el usuario %s en el sistema!", username));
            throw  new UsernameNotFoundException(String.format("Error en el login: no existe el usuario %s en el sistema!", username));
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map( role -> new SimpleGrantedAuthority((role.getNombre())))
                .peek( authority -> logger.info(" Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User( usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities );
    }

    @Override
    @Transactional( readOnly = true )
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername( username );
    }
}
