package com.lucassellis.agendadortarefas.infrastructure.security;


import com.lucassellis.agendadortarefas.business.dto.UsuarioDTO;
import com.lucassellis.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl   {

// nao entendi nada disso nao sei pra que serve
@Autowired
private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token) {
        // Chamada ao Feign
        UsuarioDTO usuarioDTO = client.buscaUsuarioPorEmail(email, token);

        // O CORRETO: usar org.springframework.security.core.userdetails.User
        return User.withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .authorities("USER") // Obrigatório para o Spring Security não dar erro
                .build();
    }
}
