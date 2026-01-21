package com.lucassellis.agendadortarefas.infrastructure.repository;


import com.lucassellis.agendadortarefas.infrastructure.entitiy.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

}
