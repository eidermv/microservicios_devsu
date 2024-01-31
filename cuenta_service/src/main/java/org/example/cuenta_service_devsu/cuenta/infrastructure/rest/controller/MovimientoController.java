package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.controller;

import jakarta.validation.Valid;
import org.example.cuenta_service_devsu.cuenta.application.command.GuardarMovimiento;
import org.example.cuenta_service_devsu.cuenta.application.query.BuscarPorRangoFechas;
import org.example.cuenta_service_devsu.cuenta.application.query.ObtenerUltimoSaldo;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.CuentaEnt;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.MovimientoEnt;
import org.example.cuenta_service_devsu.cuenta.infrastructure.db.repositoryImpl.MovimientoEntRepository;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.CuentaDto;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.MovimientoDto;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.response.RespuestaDto;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.mapper.MovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.example.cuenta_service_devsu.cuenta.application.command.EliminarPorNumeroCuenta.EliminarPorNumeroCuentaBuilder.anEliminarPorNumeroCuenta;
import static org.example.cuenta_service_devsu.cuenta.application.command.GuardarCuenta.GuardarCuentaBuilder.aGuardarCuenta;
import static org.example.cuenta_service_devsu.cuenta.application.command.GuardarMovimiento.GuardarMovimientoBuilder.aGuardarMovimiento;
import static org.example.cuenta_service_devsu.cuenta.application.query.BuscarPorNumeroCuenta.BuscarPorNumeroCuentaBuilder.aBuscarPorNumeroCuenta;
import static org.example.cuenta_service_devsu.cuenta.application.query.BuscarPorRangoFechas.BuscarPorRangoFechasBuilder.aBuscarPorRangoFechas;
import static org.example.cuenta_service_devsu.cuenta.application.query.ObtenerUltimoSaldo.ObtenerUltimoSaldoBuilder.anObtenerUltimoSaldo;

@RestController
@RequestMapping("movimientos")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MovimientoController {

    @Autowired
    private MovimientoEntRepository movimientoEntRepository;

    private static BuscarPorRangoFechas<MovimientoEnt> buscarPorRangoFechas;
    private static GuardarMovimiento<MovimientoEnt> guardarMovimiento;
    private static ObtenerUltimoSaldo obtenerUltimoSaldo;

    @Autowired
    private MovimientoMapper movimientoMapper;


    @GetMapping(value = "", params = {"fecha", "cliente"}, produces = "application/json")
    public Mono<ResponseEntity> buscarPorIdentificacion(@RequestParam String fecha, @RequestParam int cliente) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String[] fechas = fecha.split("-");
        if (fechas.length==2){
            try {
                Date fecha1 = formato.parse(fechas[0]);
                Date fecha2 = formato.parse(fechas[1]);

                buscarPorRangoFechas = aBuscarPorRangoFechas()
                        .withData1(fecha1)
                        .withData2(fecha2)
                        .withData3(cliente)
                        .withRepo(movimientoEntRepository)
                        .build();
                List<MovimientoEnt> lista = buscarPorRangoFechas.execute();
                if (Objects.isNull(lista)) {
                    return Mono.just(ResponseEntity.status(404).body(null));
                } else {
                    return Mono.just(ResponseEntity.status(200).body(lista));
                }
            } catch (ParseException e) {
                return Mono.just(ResponseEntity.status(200).body("Formato de fechas incorrectas, debe ser DD/MM/YYYY"));
            }

        } else {
            return Mono.just(ResponseEntity.status(200).body("Rango de fechas incorrectas"));
        }

    }

    @PostMapping(value = "", produces = "application/json")
    public Mono<ResponseEntity> guardarMovimiento(@Valid @RequestBody MovimientoDto movimientoDto) {
        MovimientoEnt movimientoEnt = movimientoMapper.toEntity(movimientoDto);
        obtenerUltimoSaldo = anObtenerUltimoSaldo()
                .withRepo(movimientoEntRepository)
                .withData(movimientoEnt.getCuenta().getNumero_cuenta())
                .build();
        int saldo = obtenerUltimoSaldo.execute();
        movimientoEnt.setSaldo(saldo + movimientoEnt.getValor());
        if (movimientoEnt.getSaldo()<0) {
            RespuestaDto respuestaDto = new RespuestaDto();
            respuestaDto.setData(null);
            respuestaDto.setError(-1);
            respuestaDto.setMensaje("Saldo no disponible");

            return Mono.just(ResponseEntity.status(300).body(respuestaDto.toString()));
        }
        guardarMovimiento = aGuardarMovimiento()
                .withData(movimientoEnt)
                .withRepo(movimientoEntRepository)
                .build();
        MovimientoEnt movimiento = guardarMovimiento.execute();
        if (Objects.isNull(movimiento)) {
            return Mono.just(ResponseEntity.status(304).body(null));
        } else {
            return Mono.just(ResponseEntity.status(200).body(movimientoMapper.toDto(movimiento)));
        }

    }
}
