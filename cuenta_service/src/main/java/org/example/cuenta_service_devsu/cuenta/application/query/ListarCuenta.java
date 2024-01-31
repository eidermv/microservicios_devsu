package org.example.cuenta_service_devsu.cuenta.application.query;

import org.example.cuenta_service_devsu.cuenta.domain.repository.CuentaRepo;
import org.example.cuenta_service_devsu.shared.application.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarCuenta<T> implements Query<List> {

    private static CuentaRepo repo;

    private ListarCuenta(CuentaRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<T> execute() {

        return repo.findAll();
    }


    public static final class ListarCuentaBuilder {
        private CuentaRepo repo;

        private ListarCuentaBuilder() {
        }

        public static ListarCuentaBuilder aListarCuenta() {
            return new ListarCuentaBuilder();
        }

        public ListarCuentaBuilder withRepo(CuentaRepo repo) {
            this.repo = repo;
            return this;
        }

        public ListarCuenta build() {
            return new ListarCuenta(repo);
        }
    }
}
