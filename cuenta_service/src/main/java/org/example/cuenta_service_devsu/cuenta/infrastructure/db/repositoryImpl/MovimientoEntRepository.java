package org.example.cuenta_service_devsu.cuenta.infrastructure.db.repositoryImpl;

import org.example.cuenta_service_devsu.cuenta.domain.repository.MovimientoRepo;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.MovimientoEnt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoEntRepository extends MovimientoRepo<MovimientoEnt, Integer> {

    //'2023-01-01' AND '2023-01-15';
    @Override
    //@Query(value = "SELECT * FROM movimiento m inner join cuenta c on c.numero_cuenta = m.numero_cuenta where c.clienteid = ?3 and m.fecha BETWEEN ?1 and ?2", nativeQuery = true)
    @Query(value = "SELECT m FROM MovimientoEnt m where m.cuenta.clienteid = ?3 and m.fecha BETWEEN ?1 and ?2")
    public List<MovimientoEnt> buscarPorRangoFechas(Date fecha1, Date fecha2, int clienteid);
    @Override
    @Query(value = "SELECT m.saldo " +
            "FROM movimiento m " +
            "WHERE m.numero_cuenta = ?1 " +
            "AND m.fecha =(SELECT max(m.fecha) as fecha FROM movimiento m WHERE m.numero_cuenta = ?1) " +
            "ORDER BY m.movimientoid DESC LIMIT 1", nativeQuery = true)
    public int ultimoSaldoPorCuenta(int numero_cuenta);
}
