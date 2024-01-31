package org.example.cliente_service.cliente.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaRepo<T, ID> extends JpaRepository<T, ID> {

}
