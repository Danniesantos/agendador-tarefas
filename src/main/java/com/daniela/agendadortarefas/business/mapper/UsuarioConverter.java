package com.daniela.agendadortarefas.business.mapper;

import com.daniela.agendadortarefas.business.dto.TarefasDTO;
import com.daniela.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
