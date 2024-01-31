package org.example.cliente_service.cliente.infrastructure.db.repositoryImpl;

import org.example.cliente_service.cliente.domain.repository.ClienteRepo;
import org.example.cliente_service.cliente.infrastructure.db.entity.ClienteEnt;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteEntRepository extends ClienteRepo<ClienteEnt, Integer> {
    @Override
    @Query(value = "SELECT * FROM cliente c inner join persona p on p.identificacion=c.identificacion", nativeQuery = true)
    public List<ClienteEnt> buscarTodos();
    @Override
    @Modifying
    @Query(value = "DELETE FROM ClienteEnt l WHERE l.identificacion = ?1")
    public void eliminarPorIdentificacion(int identificacion);
    @Override
    @Query(value = "SELECT l FROM ClienteEnt l WHERE l.identificacion = ?1")
    public ClienteEnt buscarPorIdentificacion(int identificacion);
    @Override
    @Query(value = "SELECT l FROM ClienteEnt l WHERE l.clienteid = ?1")
    public Optional<ClienteEnt> buscarPorId(int id);
}
