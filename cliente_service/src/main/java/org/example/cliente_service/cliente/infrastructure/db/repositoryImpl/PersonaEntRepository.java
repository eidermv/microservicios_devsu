package org.example.cliente_service.cliente.infrastructure.db.repositoryImpl;

import org.example.cliente_service.cliente.domain.repository.PersonaRepo;
import org.example.cliente_service.cliente.infrastructure.db.entity.PersonaEnt;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaEntRepository extends PersonaRepo<PersonaEnt, Integer> {

}
