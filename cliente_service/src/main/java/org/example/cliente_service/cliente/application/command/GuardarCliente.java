package org.example.cliente_service.cliente.application.command;

import org.example.cliente_service.cliente.domain.repository.ClienteRepo;
import org.example.cliente_service.shared.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class GuardarCliente<T> implements Command<T> {

    private ClienteRepo repo;
    private T data;

    @Override
    public T execute() {
        return (T) repo.save(data);
    }

    private GuardarCliente() {
    }


    public static final class GuardarClienteBuilder<T> {
        private ClienteRepo repo;
        private T data;

        private GuardarClienteBuilder() {
        }

        public static GuardarClienteBuilder aGuardarCliente() {
            return new GuardarClienteBuilder();
        }

        public GuardarClienteBuilder withRepo(ClienteRepo repo) {
            this.repo = repo;
            return this;
        }

        public GuardarClienteBuilder withData(T data) {
            this.data = data;
            return this;
        }

        public GuardarCliente build() {
            GuardarCliente guardarClientePersona = new GuardarCliente();
            guardarClientePersona.data = this.data;
            guardarClientePersona.repo = this.repo;
            return guardarClientePersona;
        }
    }
}
