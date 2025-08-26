package com.daniela.agendadortarefas.business;

import com.daniela.agendadortarefas.business.dto.TarefasDTO;
import com.daniela.agendadortarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private TarefasRepository tarefasRepository;

    public TarefasDTO gravarTarefa(TarefasDTO dto){
        return tarefasRepository.save();
    }
}
