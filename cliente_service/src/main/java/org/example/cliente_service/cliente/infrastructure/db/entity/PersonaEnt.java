package org.example.cliente_service.cliente.infrastructure.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonaEnt {
    @Id
    @JsonProperty("id")
    private int identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private long telefono;
}
