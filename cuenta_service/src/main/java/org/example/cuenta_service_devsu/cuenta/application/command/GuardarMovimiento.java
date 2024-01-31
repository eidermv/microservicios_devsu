package org.example.cuenta_service_devsu.cuenta.application.command;

import org.example.cuenta_service_devsu.cuenta.domain.repository.MovimientoRepo;
import org.example.cuenta_service_devsu.shared.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class GuardarMovimiento<T> implements Command<T> {

    private MovimientoRepo repo;
    private T data;

    @Override
    public T execute() {
        return (T) repo.save(data);
    }

    private GuardarMovimiento() {
    }


    public static final class GuardarMovimientoBuilder<T> {
        private MovimientoRepo repo;
        private T data;

        private GuardarMovimientoBuilder() {
        }

        public static GuardarMovimientoBuilder aGuardarMovimiento() {
            return new GuardarMovimientoBuilder();
        }

        public GuardarMovimientoBuilder withRepo(MovimientoRepo repo) {
            this.repo = repo;
            return this;
        }

        public GuardarMovimientoBuilder withData(T data) {
            this.data = data;
            return this;
        }

        public GuardarMovimiento build() {
            GuardarMovimiento guardarMovimiento = new GuardarMovimiento();
            guardarMovimiento.data = this.data;
            guardarMovimiento.repo = this.repo;
            return guardarMovimiento;
        }
    }
}
