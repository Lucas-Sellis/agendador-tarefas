package com.lucassellis.agendadortarefas.infrastructure.repository;


import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    // O nome do método deve ser findBy + NomeDoCampo + Between
    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefasEntity> findByEmailUsuario(String email);
}
