package org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "cuenta")
public class CuentaEnt {

    @Id
    private int numero_cuenta;
    private int clienteid;
    private String tipo;
    private int saldo_inicial;
    private boolean estado;
    /*@OneToMany(mappedBy = "cuenta", cascade = CascadeType.REMOVE)
    @JsonProperty("movimientos")
    private List<MovimientoEnt> movimientos;*/
}
