package com.lucassellis.agendadortarefas.infrastructure.client;

import com.lucassellis.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//nao entendi nada nao sei pra que serve
@FeignClient(name= "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    // 1. Removidas as chaves {}: Interfaces apenas declaram o método.
    // 2. Corrigido de "Authotization" para "Authorization".
    @GetMapping("/usuario") // Verifique se o endpoint no outro serviço é /usuario
    UsuarioDTO buscaUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String token);
}
