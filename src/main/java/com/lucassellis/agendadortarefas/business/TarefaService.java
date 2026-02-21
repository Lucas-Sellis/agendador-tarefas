package com.lucassellis.agendadortarefas.business;

import com.lucassellis.agendadortarefas.business.dto.TarefasDTORecord;
import com.lucassellis.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.lucassellis.agendadortarefas.business.mapper.TarefasConverter;
import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import com.lucassellis.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucassellis.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.lucassellis.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.lucassellis.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefaService {

     final TarefasRepository tarefasRepository;
     final TarefasConverter tarefaConverter;
     final JwtUtil jwtUtil;
     final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTORecord gravarTarefa (String token, TarefasDTORecord dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(),
                dto.descricao(),LocalDateTime.now(),dto.dataEvento(), email,null,StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);

        return  tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));

        //pelo que entendi esse metodo grava tarefa, vai receber uma tarefa dto
        // vai receber um token pra pegar o nome do usuario
        // vai puxar esse nome do usuario
        //depois que receber ele vai definir uma data e hoa atual
        //        vai colocar como pendente usando o enum
        //vai converter para tarefa dto
        // vai retornar a tarefa dtop para entity e salvar no repository

    }

    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){

        return tarefaConverter.paraListaTarefasDTORecord(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

   public List<TarefasDTORecord> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefasEntity>listaTarefas=tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTORecord(listaTarefas);
    }

    public void deletaTarefaPorId(String id){

        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente" + id, e.getCause());
        }
    }

    public TarefasDTORecord alteraStatus(StatusNotificacaoEnum status, String id){

        try {
            // 1. Busca a entidade ou lança a exceção diretamente
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            // 2. Atualiza o status
            entity.setStatusNotificacaoEnum(status);
            // 3. Salva e converte
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " +e.getCause());
        }

    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id){
        try {
            // 1. Busca a entidade ou lança a exceção diretamente
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            // 2. Atualiza o status
            tarefaUpdateConverter.updateTarefas(dto,entity);
            // 3. Salva e converte
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " +e.getCause());
        }

    }



}
