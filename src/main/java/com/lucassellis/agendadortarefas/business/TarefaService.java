package com.lucassellis.agendadortarefas.business;

import com.lucassellis.agendadortarefas.business.dto.TarefasDTO;
import com.lucassellis.agendadortarefas.business.mapper.TarefasConverter;
import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import com.lucassellis.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucassellis.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.lucassellis.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa (String token, TarefasDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDatacriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return  tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        //pelo que entendi esse metodo grava tarefa, vai receber uma tarefa dto
        // vai receber um token pra pegar o nome do usuario
        // vai puxar esse nome do usuario
        //depois que receber ele vai definir uma data e hoa atual
        //        vai colocar como pendente usando o enum
        //vai converter para tarefa dto
        // vai retornar a tarefa dtop para entity e salvar no repository

    }
}
