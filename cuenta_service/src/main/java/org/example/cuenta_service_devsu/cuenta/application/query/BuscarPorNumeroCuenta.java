package org.example.cuenta_service_devsu.cuenta.application.query;

import org.example.cuenta_service_devsu.cuenta.domain.repository.CuentaRepo;
import org.example.cuenta_service_devsu.shared.application.query.Query;
import org.springframework.stereotype.Component;

@Component
public class BuscarPorNumeroCuenta<T> implements Query<T> {

    private CuentaRepo repo;
    private int data;

    private BuscarPorNumeroCuenta() {
    }

    @Override
    public T execute() {
        return (T) repo.findById(data);
    }


    public static final class BuscarPorNumeroCuentaBuilder {
        private CuentaRepo repo;
        private int data;

        private BuscarPorNumeroCuentaBuilder() {
        }

        public static BuscarPorNumeroCuentaBuilder aBuscarPorNumeroCuenta() {
            return new BuscarPorNumeroCuentaBuilder();
        }

        public BuscarPorNumeroCuentaBuilder withRepo(CuentaRepo repo) {
            this.repo = repo;
            return this;
        }

        public BuscarPorNumeroCuentaBuilder withData(int data) {
            this.data = data;
            return this;
        }

        public BuscarPorNumeroCuenta build() {
            BuscarPorNumeroCuenta buscarPorNumeroCuenta = new BuscarPorNumeroCuenta();
            buscarPorNumeroCuenta.repo = this.repo;
            buscarPorNumeroCuenta.data = this.data;
            return buscarPorNumeroCuenta;
        }
    }
}
