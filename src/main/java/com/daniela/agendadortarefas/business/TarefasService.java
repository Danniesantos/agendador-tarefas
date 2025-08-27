package com.daniela.agendadortarefas.business;

import com.daniela.agendadortarefas.business.dto.TarefasDTO;
import com.daniela.agendadortarefas.business.mapper.TarefasConverter;
import com.daniela.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.daniela.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.daniela.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.daniela.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto);
        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }
}
