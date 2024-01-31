package org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.cuenta_service_devsu.cuenta.domain.model.Cuenta;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "movimiento")
@Inheritance(strategy = InheritanceType.JOINED)
public class MovimientoEnt {
    @Id
    @JsonProperty("id")
    private int movimientoid;
    private Date fecha;
    private String tipo;
    private int valor;
    private int saldo;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "numero_cuenta")
    private CuentaEnt cuenta;

}
