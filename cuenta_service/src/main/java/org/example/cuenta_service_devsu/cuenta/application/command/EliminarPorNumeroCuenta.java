package org.example.cuenta_service_devsu.cuenta.application.command;


import org.example.cuenta_service_devsu.cuenta.domain.repository.CuentaRepo;
import org.example.cuenta_service_devsu.shared.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class EliminarPorNumeroCuenta implements Command<Boolean> {

    private CuentaRepo repo;
    private int data;

    @Override
    public Boolean execute() {
        if (repo.existsById(data)) {
            repo.deleteById(data);
            return true;
        }
        return false;
    }


    public static final class EliminarPorNumeroCuentaBuilder {
        private CuentaRepo repo;
        private int data;

        private EliminarPorNumeroCuentaBuilder() {
        }

        public static EliminarPorNumeroCuentaBuilder anEliminarPorNumeroCuenta() {
            return new EliminarPorNumeroCuentaBuilder();
        }

        public EliminarPorNumeroCuentaBuilder withRepo(CuentaRepo repo) {
            this.repo = repo;
            return this;
        }

        public EliminarPorNumeroCuentaBuilder withData(int data) {
            this.data = data;
            return this;
        }

        public EliminarPorNumeroCuenta build() {
            EliminarPorNumeroCuenta eliminarPorNumeroCuenta = new EliminarPorNumeroCuenta();
            eliminarPorNumeroCuenta.repo = this.repo;
            eliminarPorNumeroCuenta.data = this.data;
            return eliminarPorNumeroCuenta;
        }
    }
}
