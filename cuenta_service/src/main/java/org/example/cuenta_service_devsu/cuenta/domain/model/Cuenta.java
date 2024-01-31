package org.example.cuenta_service_devsu.cuenta.domain.model;

import java.util.List;

public class Cuenta {

    private int numero_cuenta;
    private int clienteid;
    private String tipo;
    private int saldo_inicial;
    private boolean estado;
    private List<Movimiento> movimientos;


    public int getNumeroCuenta() {
        return numero_cuenta;
    }

    public void setNumeroCuenta(int numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    public String getTipo() {
        return tipo;
    }

}
