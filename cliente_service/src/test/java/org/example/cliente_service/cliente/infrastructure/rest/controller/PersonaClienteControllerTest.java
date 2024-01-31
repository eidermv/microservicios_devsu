package org.example.cliente_service.cliente.infrastructure.rest.controller;

import org.example.cliente_service.cliente.application.query.BuscarPorIdentificacion;
import org.example.cliente_service.cliente.infrastructure.db.entity.ClienteEnt;
import org.example.cliente_service.cliente.infrastructure.db.repositoryImpl.ClienteEntRepository;
import org.example.cliente_service.cliente.infrastructure.db.repositoryImpl.PersonaEntRepository;
import org.example.cliente_service.cliente.infrastructure.rest.mapper.ClienteMapper;
import org.example.cliente_service.cliente.infrastructure.rest.mapper.PersonaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonaClienteController.class)
@ComponentScan(basePackages = {"com.example.cliente_service.infrastructure"})
public class PersonaClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static BuscarPorIdentificacion<ClienteEnt> buscarPorIdentificacion;

    @MockBean
    private PersonaEntRepository personaEntRepository;
    @MockBean
    private ClienteEntRepository clienteEntRepository;
    @MockBean
    private ClienteMapper clienteMapper;
    @MockBean
    private PersonaMapper personaMapper;

    @Test
    public void givenIdentificacion_whenBuscarPorIdentificacion_thenReturnCliente() throws Exception {
        ClienteEnt clienteEnt = new ClienteEnt();

        clienteEnt.setNombre("Prueba1");
        clienteEnt.setIdentificacion(11111);
        clienteEnt.setGenero("Masculino");
        clienteEnt.setEdad(18);
        clienteEnt.setDireccion("Prueba");
        clienteEnt.setTelefono(3666666666L);
        clienteEnt.setContrasena("2222");
        clienteEnt.setEstado(true);
        given(buscarPorIdentificacion.execute()).willReturn(clienteEnt);
        mockMvc.perform(get("/clientes/987654"))
                .andExpect(status().isOk());
    }
}
