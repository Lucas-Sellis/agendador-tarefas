package com.lucassellis.agendadortarefas.business.mapper;

import com.lucassellis.agendadortarefas.business.dto.TarefasDTORecord;
import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    // Atualiza só campos preenchidos do DTO
    // Null do DTO não sobrescreve valores da entity
    void updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
