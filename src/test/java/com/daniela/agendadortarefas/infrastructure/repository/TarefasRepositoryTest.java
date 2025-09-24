package com.daniela.agendadortarefas.infrastructure.repository;

import com.daniela.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.daniela.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class TarefasRepositoryTest {

    @Autowired
    TarefasRepository tarefasRepository;

    @Test
    @DisplayName("Should find tarefa from data evento and status")
    void findByDataEventoBetweenAndStatusNotificacaoEnumSucess() {
        tarefasRepository.deleteAll();

        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now(fixedClock).truncatedTo(ChronoUnit.MILLIS);
        TarefasEntity tarefas = new TarefasEntity();
        tarefas.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefas.setDataEvento(now.plusHours(1));

        tarefasRepository.save(tarefas);

        List<TarefasEntity> result = tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                now,
                now.plusHours(2),
                StatusNotificacaoEnum.PENDENTE);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStatusNotificacaoEnum()).isEqualTo(StatusNotificacaoEnum.PENDENTE);
        assertThat(result.get(0).getDataEvento()).isEqualTo(tarefas.getDataEvento());
    }

    @Test
    @DisplayName("Should return empty list when no tarefa matches date range")
    void findByDataEventoBetweenAndStatusNotificacaoEnumNoResults() {
        tarefasRepository.deleteAll();

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        TarefasEntity tarefas = new TarefasEntity();
        tarefas.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefas.setDataEvento(now.plusDays(1));

        tarefasRepository.save(tarefas);

        List<TarefasEntity> result = tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                now.minusDays(3),
                now.plusDays(1),
                StatusNotificacaoEnum.PENDENTE);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return empty list when status does not match")
    void findByDataEventoBetweenAndStatusNotificacaoEnumWrongStatus() {
        tarefasRepository.deleteAll();

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        TarefasEntity tarefas = new TarefasEntity();
        tarefas.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefas.setDataEvento(now.plusHours(1));

        tarefasRepository.save(tarefas);

        List<TarefasEntity> result = tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                now,
                now.plusHours(2),
                StatusNotificacaoEnum.CANCELADO);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return empty list when start date is after end date")
    void findByDataEventoBetweenAndStatusNotificacaoEnumInvalidRange() {
        tarefasRepository.deleteAll();

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        TarefasEntity tarefas = new TarefasEntity();
        tarefas.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefas.setDataEvento(now.plusHours(1));

        tarefasRepository.save(tarefas);

        List<TarefasEntity> result = tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                now.plusHours(2),
                now,
                StatusNotificacaoEnum.PENDENTE);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should find email usuario when exists")
    void findByEmailUsuarioSucess() {
        tarefasRepository.deleteAll();
        TarefasEntity tarefas = new TarefasEntity();
        tarefas.setEmailUsuario("teste@gmail.com");
        tarefasRepository.save(tarefas);

        List<TarefasEntity> result = tarefasRepository.findByEmailUsuario("teste@gmail.com");

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmailUsuario()).isEqualTo("teste@gmail.com");
    }

    @Test
    @DisplayName("Should find email usuario not exists")
    void findByEmailUsuarioNotExists() {
        tarefasRepository.deleteAll();
        TarefasEntity tarefas = new TarefasEntity();
        tarefas.setEmailUsuario("teste@gmail.com");
        tarefasRepository.save(tarefas);

        List<TarefasEntity> result = tarefasRepository.findByEmailUsuario("naoexiste@gmail.com");

        assertThat(result).isEmpty();
    }
}