package org.example.cuenta_service_devsu.cuenta.infrastructure.db.repositoryImpl;

import org.example.cuenta_service_devsu.cuenta.domain.repository.CuentaRepo;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.CuentaEnt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaEntRepository extends CuentaRepo<CuentaEnt, Integer> {

}
