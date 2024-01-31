package org.example.cliente_service.cliente.infrastructure.rest.controller;

import jakarta.validation.Valid;
import org.example.cliente_service.cliente.application.command.EliminarPorIdentificacion;
import org.example.cliente_service.cliente.application.command.GuardarCliente;
import org.example.cliente_service.cliente.application.command.GuardarPersona;
import org.example.cliente_service.cliente.application.query.BuscarClientePorId;
import org.example.cliente_service.cliente.application.query.BuscarPorIdentificacion;
import org.example.cliente_service.cliente.application.query.ListarCliente;
import org.example.cliente_service.cliente.infrastructure.db.entity.ClienteEnt;
import org.example.cliente_service.cliente.infrastructure.db.entity.PersonaEnt;
import org.example.cliente_service.cliente.infrastructure.db.repositoryImpl.ClienteEntRepository;
import org.example.cliente_service.cliente.infrastructure.db.repositoryImpl.PersonaEntRepository;
import org.example.cliente_service.cliente.infrastructure.rest.dto.request.PersonaClienteDto;
import org.example.cliente_service.cliente.infrastructure.rest.mapper.ClienteMapper;
import org.example.cliente_service.cliente.infrastructure.rest.mapper.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.example.cliente_service.cliente.application.command.EliminarPorIdentificacion.EliminarPorIdentificacionBuilder.anEliminarPorIdentificacion;
import static org.example.cliente_service.cliente.application.command.GuardarPersona.GuardarPersonaBuilder.aGuardarPersona;
import static org.example.cliente_service.cliente.application.command.GuardarCliente.GuardarClienteBuilder.aGuardarCliente;
import static org.example.cliente_service.cliente.application.query.BuscarClientePorId.BuscarClientePorIdBuilder.aBuscarClientePorId;
import static org.example.cliente_service.cliente.application.query.BuscarPorIdentificacion.BuscarPorIdentificacionBuilder.aBuscarPorIdentificacion;
import static org.example.cliente_service.cliente.application.query.ListarCliente.ListarClienteBuilder.aListarCliente;


@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PersonaClienteController {

    @Autowired
    private PersonaEntRepository personaEntRepository;
    @Autowired
    private ClienteEntRepository clienteEntRepository;

    private static ListarCliente<ClienteEnt> clientes;
    private static BuscarPorIdentificacion<ClienteEnt> buscarPorIdentificacion;
    private static BuscarClientePorId<Optional<ClienteEnt>> buscarClientePorId;
    private static GuardarPersona<PersonaEnt> guardarPersona;
    private static GuardarCliente<ClienteEnt> guardarCliente;
   private static EliminarPorIdentificacion eliminarPorIdentificacion;

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private PersonaMapper personaMapper;


    @GetMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> listarTodos() {
        clientes = aListarCliente()
                .withClienteRepo(clienteEntRepository)
                .build();
        List<PersonaClienteDto> dtoList = clientes.execute().stream().map(clienteEnt -> clienteMapper.toDto(clienteEnt)).toList();
        return Mono.just(ResponseEntity.status(200).body(dtoList));
    }
    @GetMapping(value = "", params = {"clienteid"}, produces = "application/json")
    public Mono<ResponseEntity> buscarClientePorId(@RequestParam int clienteid) {
        buscarClientePorId = aBuscarClientePorId()
                .withData(clienteid)
                .withRepo(clienteEntRepository)
                .build();
        Optional<ClienteEnt> lista = buscarClientePorId.execute();
        if (!lista.isPresent()) {
            return Mono.just(ResponseEntity.status(404).body(null));
        } else {
            return Mono.just(ResponseEntity.status(200).body(clienteMapper.toDto(lista.get())));
        }
    }

    @GetMapping(value = "/{identificacion}", produces = "application/json")
    public Mono<ResponseEntity> buscarPorIdentificacion(@PathVariable int identificacion) {
        buscarPorIdentificacion = aBuscarPorIdentificacion()
                    .withData(identificacion)
                    .withRepo(clienteEntRepository)
                    .build();
            ClienteEnt lista = buscarPorIdentificacion.execute();
            if (Objects.isNull(lista)) {
                return Mono.just(ResponseEntity.status(404).body(null));
            } else {
                return Mono.just(ResponseEntity.status(200).body(clienteMapper.toDto(lista)));
            }
    }

    @PostMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> guardarClientePersona(@Valid @RequestBody PersonaClienteDto personaClienteDto) {
        /*PersonaEnt personaEnt = personaMapper.toEntity(personaClienteDto);

        guardarPersona = aGuardarPersona()
                .withData(personaEnt)
                .withRepo(personaEntRepository)
                .build();
        return Mono.just(guardarPersona.execute()).map(persona -> {
            if (!Objects.isNull(persona)) {*/
                ClienteEnt clienteEnt = clienteMapper.toEntity(personaClienteDto);
                // clienteEnt.setIdentificacion(persona.getIdentificacion());
                guardarCliente = aGuardarCliente()
                        .withData(clienteEnt)
                        .withRepo(clienteEntRepository)
                        .build();
                ClienteEnt cliente = guardarCliente.execute();
                if (Objects.isNull(cliente)) {
                    return Mono.just(ResponseEntity.status(304).body(null));
                } else {
                    return Mono.just(ResponseEntity.status(200).body(clienteMapper.toDto(cliente)));
                }
            /*} else {
                return ResponseEntity.status(304).body(null);
            }
        });*/

    }

    @PutMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> actualizarClientePersona(@Valid @RequestBody PersonaClienteDto personaClienteDto) {
        ClienteEnt clienteEnt = clienteMapper.toEntity(personaClienteDto);
        guardarCliente = aGuardarCliente()
                .withData(clienteEnt)
                .withRepo(clienteEntRepository)
                .build();
        ClienteEnt cliente = guardarCliente.execute();
        if (Objects.isNull(cliente)) {
            return Mono.just(ResponseEntity.status(304).body(null));
        } else {
            return Mono.just(ResponseEntity.status(200).body(clienteMapper.toDto(cliente)));
        }

    }

    @DeleteMapping(value = "/{identificacion}", produces = "application/json")
    public Mono<ResponseEntity> eliminarPorIdentificacion(@PathVariable int identificacion) {
        buscarPorIdentificacion = aBuscarPorIdentificacion()
                .withData(identificacion)
                .withRepo(clienteEntRepository)
                .build();
        ClienteEnt lista = buscarPorIdentificacion.execute();
        if (!Objects.isNull(lista)) {
            eliminarPorIdentificacion = anEliminarPorIdentificacion()
                    .withData(identificacion)
                    .withRepo(personaEntRepository)
                    .withClienteRepo(clienteEntRepository)
                    .build();
            if (Boolean.TRUE.equals(eliminarPorIdentificacion.execute())) {
                return Mono.just(ResponseEntity.status(200).body(true));
            } else {
                return Mono.just(ResponseEntity.status(404).body(false));
            }
        } else {
            return Mono.just(ResponseEntity.status(404).body(false));
        }
    }
}
