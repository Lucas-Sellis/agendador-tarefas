package com.lucassellis.agendadortarefas.business.mapper;

import com.lucassellis.agendadortarefas.business.dto.TarefasDTO;
import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import org.mapstruct.Mapper;


// aqui em vez de usar o builder estamos usando esse mapper que e muito mais faceil de passar dto pra entiti depois qeuro resumo
@Mapper(componentModel = "spring")

public interface TarefasConverter {
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}



/*
  para usar o mapper precisa colocar isso no gradle
  implementation 'org.mapstruct:mapstruct:1.5.3.Final'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.3.Final'
    annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0'*/
