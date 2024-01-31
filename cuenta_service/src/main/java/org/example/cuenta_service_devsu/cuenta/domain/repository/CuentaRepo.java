package org.example.cuenta_service_devsu.cuenta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CuentaRepo<T, ID> extends JpaRepository<T, ID> {

}
