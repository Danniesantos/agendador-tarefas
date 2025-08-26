package com.daniela.agendadortarefas.business.mapper;

import com.daniela.agendadortarefas.business.dto.TarefasDTO;
import com.daniela.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id" , target = "id")
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
