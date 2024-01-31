package org.example.cuenta_service_devsu.cuenta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface MovimientoRepo<T, ID> extends JpaRepository<T, ID> {

 public List<T> buscarPorRangoFechas(Date fecha1, Date fecha2, int clienteid);
 public int ultimoSaldoPorCuenta(int numero_cuenta);
}
