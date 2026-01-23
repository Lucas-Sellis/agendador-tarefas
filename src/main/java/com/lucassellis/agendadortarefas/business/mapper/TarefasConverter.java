package com.lucassellis.agendadortarefas.business.mapper;

import com.lucassellis.agendadortarefas.business.dto.TarefasDTO;
import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


// aqui em vez de usar o builder estamos usando esse mapper que e muito mais faceil de passar dto pra entiti depois qeuro resumo
@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target ="id")
    @Mapping(source = "dataEvento", target ="dataEvento")
    @Mapping(source = "dataCriacao", target ="dataCriacao")

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}



/*
  para usar o mapper precisa colocar isso no gradle
  implementation 'org.mapstruct:mapstruct:1.5.3.Final'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.3.Final'
    annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0'*/
