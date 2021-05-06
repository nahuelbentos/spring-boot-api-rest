package com.nahuelbentos.springbootbackendapirest.auth;

import com.nahuelbentos.springbootbackendapirest.model.entity.Usuario;
import com.nahuelbentos.springbootbackendapirest.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    private final UsuarioService usuarioService;

    public InfoAdicionalToken(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Usuario usuario = usuarioService.findByUsername( authentication.getName() );

        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional", " Hola que tal? ".concat( authentication.getName() ));

        info.put("nombre",  usuario.getNombre() );
        info.put("apellido",  usuario.getApellido() );
        info.put("email",  usuario.getEmail() );

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation( info );
        return accessToken;
    }
}
