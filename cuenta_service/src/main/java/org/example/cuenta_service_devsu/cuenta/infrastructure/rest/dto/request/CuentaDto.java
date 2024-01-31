package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CuentaDto {

    @NotNull
    private int numero_cuenta;
    @NotNull
    private int clienteid;
    private String tipo;
    @NotNull
    private int saldo_inicial;
    private boolean estado;
    private String nombre;

}
