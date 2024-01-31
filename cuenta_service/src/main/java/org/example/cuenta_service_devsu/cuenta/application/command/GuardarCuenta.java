package org.example.cuenta_service_devsu.cuenta.application.command;

import org.example.cuenta_service_devsu.cuenta.domain.repository.CuentaRepo;
import org.example.cuenta_service_devsu.shared.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class GuardarCuenta<T> implements Command<T> {

    private CuentaRepo repo;
    private T data;

    @Override
    public T execute() {
        return (T) repo.save(data);
    }

    private GuardarCuenta() {
    }


    public static final class GuardarCuentaBuilder<T> {
        private CuentaRepo repo;
        private T data;

        private GuardarCuentaBuilder() {
        }

        public static GuardarCuentaBuilder aGuardarCuenta() {
            return new GuardarCuentaBuilder();
        }

        public GuardarCuentaBuilder withRepo(CuentaRepo repo) {
            this.repo = repo;
            return this;
        }

        public GuardarCuentaBuilder withData(T data) {
            this.data = data;
            return this;
        }

        public GuardarCuenta build() {
            GuardarCuenta guardarCuenta = new GuardarCuenta();
            guardarCuenta.data = this.data;
            guardarCuenta.repo = this.repo;
            return guardarCuenta;
        }
    }
}
