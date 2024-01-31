package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.controller;

import io.vertx.core.json.JsonObject;
import jakarta.validation.Valid;
import org.example.cuenta_service_devsu.cuenta.application.command.EliminarPorNumeroCuenta;
import org.example.cuenta_service_devsu.cuenta.application.command.GuardarCuenta;
import org.example.cuenta_service_devsu.cuenta.application.query.BuscarPorNumeroCuenta;
import org.example.cuenta_service_devsu.cuenta.application.query.ConsultarClienteTitular;
import org.example.cuenta_service_devsu.cuenta.application.query.ListarCuenta;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.CuentaEnt;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.repositoryImpl.CuentaEntRepository;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.CuentaActualizarDto;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.CuentaDto;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.example.cuenta_service_devsu.cuenta.application.command.EliminarPorNumeroCuenta.EliminarPorNumeroCuentaBuilder.anEliminarPorNumeroCuenta;
import static org.example.cuenta_service_devsu.cuenta.application.command.GuardarCuenta.GuardarCuentaBuilder.aGuardarCuenta;
import static org.example.cuenta_service_devsu.cuenta.application.query.BuscarPorNumeroCuenta.BuscarPorNumeroCuentaBuilder.aBuscarPorNumeroCuenta;
import static org.example.cuenta_service_devsu.cuenta.application.query.ListarCuenta.ListarCuentaBuilder.aListarCuenta;

@RestController
@RequestMapping("cuentas")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CuentaController {

    @Autowired
    private CuentaEntRepository cuentaEntRepository;

    private static ListarCuenta<CuentaEnt> cuentas;
    private static BuscarPorNumeroCuenta<Optional<CuentaEnt>> buscarPorNumeroCuenta;
    private static GuardarCuenta<CuentaEnt> guardarCuenta;
   private static EliminarPorNumeroCuenta eliminarPorNumeroCuenta;
   @Autowired
   private ConsultarClienteTitular consultarClienteTitular;

    @Autowired
    private CuentaMapper cuentaMapper;


    @GetMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> listarTodos() {
        cuentas = aListarCuenta()
                .withRepo(cuentaEntRepository)
                .build();
        List<CuentaDto> dtoList = cuentas.execute().stream().map(clienteEnt -> {
            CuentaDto cuentaDto = cuentaMapper.toDto(clienteEnt);
            consultarClienteTitular.setClienteid(cuentaDto.getClienteid());
            return (CuentaDto) consultarClienteTitular.execute().map(o -> {
                JsonObject cliente = new JsonObject(String.valueOf(o));
                if (cliente.containsKey("error")) {
                    cuentaDto.setNombre("No se pudo obtener titular");
                } else {
                    cuentaDto.setNombre(cliente.getString("nombre"));
                }
                return cuentaDto;
            }).block();
        }).toList();
        return Mono.just(ResponseEntity.status(200).body(dtoList));
    }

    @GetMapping(value = "/{numero_cuenta}", produces = "application/json")
    public Mono<ResponseEntity> buscarPorNumeroCuenta(@PathVariable int numero_cuenta) {
        buscarPorNumeroCuenta = aBuscarPorNumeroCuenta()
                    .withData(numero_cuenta)
                    .withRepo(cuentaEntRepository)
                    .build();
            Optional<CuentaEnt> lista = buscarPorNumeroCuenta.execute();
            if (!lista.isPresent()) {
                return Mono.just(ResponseEntity.status(404).body(null));
            } else {
                CuentaDto cuentaDto = cuentaMapper.toDto(lista.get());
                consultarClienteTitular.setClienteid(cuentaDto.getClienteid());
                return consultarClienteTitular.execute().map(o -> {
                    JsonObject cliente = new JsonObject(String.valueOf(o));
                    if (cliente.containsKey("error")) {
                        cuentaDto.setNombre("No se pudo obtener titular");
                    } else {
                        cuentaDto.setNombre(cliente.getString("nombre"));
                    }
                    return ResponseEntity.status(200).body(cuentaDto);
                });
            }
    }

    @PostMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> guardarCuenta(@Valid @RequestBody CuentaDto cuentaDto) {
        CuentaEnt cuentaEnt = cuentaMapper.toEntity(cuentaDto);
        guardarCuenta = aGuardarCuenta()
                .withData(cuentaEnt)
                .withRepo(cuentaEntRepository)
                .build();
        CuentaEnt cuenta = guardarCuenta.execute();
        if (Objects.isNull(cuenta)) {
            return Mono.just(ResponseEntity.status(304).body(null));
        } else {
            return Mono.just(ResponseEntity.status(200).body(cuentaMapper.toDto(cuenta)));
        }

    }

    @PutMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> actualizarCuenta(@Valid @RequestBody CuentaActualizarDto cuentaDto) {
        buscarPorNumeroCuenta = aBuscarPorNumeroCuenta()
                .withData(cuentaDto.getNumero_cuenta())
                .withRepo(cuentaEntRepository)
                .build();
        Optional<CuentaEnt> lista = buscarPorNumeroCuenta.execute();
        if (!lista.isPresent()) {
            return Mono.just(ResponseEntity.status(404).body(null));
        } else {
            CuentaEnt cuentaEnt = cuentaMapper.toEntity(cuentaDto);
            cuentaEnt.setSaldo_inicial(lista.get().getSaldo_inicial());
            guardarCuenta = aGuardarCuenta()
                    .withData(cuentaEnt)
                    .withRepo(cuentaEntRepository)
                    .build();
            CuentaEnt cuenta = guardarCuenta.execute();
            if (Objects.isNull(cuenta)) {
                return Mono.just(ResponseEntity.status(304).body(null));
            } else {
                return Mono.just(ResponseEntity.status(200).body(cuentaMapper.toDto(cuenta)));
            }
        }

    }

    @DeleteMapping(value = "/{numero_cuenta}", produces = "application/json")
    public Mono<ResponseEntity> eliminarPorNumeroCuenta(@PathVariable int numero_cuenta) {
        buscarPorNumeroCuenta = aBuscarPorNumeroCuenta()
                .withData(numero_cuenta)
                .withRepo(cuentaEntRepository)
                .build();
        Optional<CuentaEnt> cuenta = buscarPorNumeroCuenta.execute();
        if (cuenta.isPresent()) {
            eliminarPorNumeroCuenta = anEliminarPorNumeroCuenta()
                    .withData(numero_cuenta)
                    .withRepo(cuentaEntRepository)
                    .build();
            if (Boolean.TRUE.equals(eliminarPorNumeroCuenta.execute())) {
                return Mono.just(ResponseEntity.status(200).body(true));
            } else {
                return Mono.just(ResponseEntity.status(404).body(false));
            }
        } else {
            return Mono.just(ResponseEntity.status(404).body(false));
        }
    }
}
