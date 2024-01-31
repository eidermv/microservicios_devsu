package org.example.cliente_service.cliente.infrastructure.db.repositoryImpl;

import org.example.cliente_service.cliente.infrastructure.db.entity.ClienteEnt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClienteEntRepositoryTest {

    @Autowired
    private ClienteEntRepository repository;

    @Test
    @Transactional
    void givenClientEnt_whenEliminarPorIdentificacion_thenDelete() {
        ClienteEnt clienteEnt = new ClienteEnt();

        clienteEnt.setNombre("Prueba1");
        clienteEnt.setIdentificacion(11111);
        clienteEnt.setGenero("Masculino");
        clienteEnt.setEdad(18);
        clienteEnt.setDireccion("Prueba");
        clienteEnt.setTelefono(3666666666L);
        clienteEnt.setContrasena("2222");
        clienteEnt.setEstado(true);

        repository.save(clienteEnt);

        repository.eliminarPorIdentificacion(11111);

        List<ClienteEnt> list = repository.buscarTodos();

        assertTrue(list.stream().noneMatch(listaEnt1 -> listaEnt1.getIdentificacion() == 11111));
    }
}
