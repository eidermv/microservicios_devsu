package org.example.cliente_service.cliente.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonaClienteDto {
    @NotNull
    private int identificacion;
    @NotNull
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private long telefono;
    private int clienteid;
    @NotNull
    private String contrasena;
    private boolean estado;

}
