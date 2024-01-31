package org.example.cliente_service.cliente.infrastructure.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "identificacion",referencedColumnName = "identificacion")
public class ClienteEnt extends PersonaEnt {
    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("clienteid")
    private int clienteid;
    private String contrasena;
    private boolean estado;
    @Column(name="identificacion",insertable = false,updatable = false)
    private int identificacion;

}
