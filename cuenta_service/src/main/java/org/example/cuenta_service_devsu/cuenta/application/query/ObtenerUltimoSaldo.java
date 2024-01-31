package org.example.cuenta_service_devsu.cuenta.application.query;

import org.example.cuenta_service_devsu.cuenta.domain.repository.CuentaRepo;
import org.example.cuenta_service_devsu.cuenta.domain.repository.MovimientoRepo;
import org.example.cuenta_service_devsu.shared.application.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ObtenerUltimoSaldo implements Query<Integer> {

    private MovimientoRepo repo;
    private int data;

    private ObtenerUltimoSaldo() {
    }

    @Override
    public Integer execute() {
        return repo.ultimoSaldoPorCuenta(data);
    }


    public static final class ObtenerUltimoSaldoBuilder {
        private MovimientoRepo repo;
        private int data;

        private ObtenerUltimoSaldoBuilder() {
        }

        public static ObtenerUltimoSaldoBuilder anObtenerUltimoSaldo() {
            return new ObtenerUltimoSaldoBuilder();
        }

        public ObtenerUltimoSaldoBuilder withRepo(MovimientoRepo repo) {
            this.repo = repo;
            return this;
        }

        public ObtenerUltimoSaldoBuilder withData(int data) {
            this.data = data;
            return this;
        }

        public ObtenerUltimoSaldo build() {
            ObtenerUltimoSaldo obtenerUltimoSaldo = new ObtenerUltimoSaldo();
            obtenerUltimoSaldo.data = this.data;
            obtenerUltimoSaldo.repo = this.repo;
            return obtenerUltimoSaldo;
        }
    }
}
