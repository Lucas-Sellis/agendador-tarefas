package com.lucassellis.agendadortarefas.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioDTO {


    // email que o usuário vai usar para login
    private String email;

    // senha normal (texto puro) — depois eu vou criptografar no service
    private String senha;


}
