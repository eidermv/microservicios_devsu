package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaActualizarDto {

    @NotNull
    private int numero_cuenta;
    @NotNull
    private int clienteid;
    private String tipo;
    private boolean estado;

}
