package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class MovimientoDto {
    private int movimientoid;
    @NotNull
    private Date fecha;
    private String tipo;
    @NotNull
    private int valor;
    @NotNull
    private int numero_cuenta;

}
