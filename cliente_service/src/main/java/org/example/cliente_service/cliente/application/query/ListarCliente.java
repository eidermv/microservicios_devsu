package org.example.cliente_service.cliente.application.query;

import org.example.cliente_service.cliente.domain.repository.ClienteRepo;
import org.example.cliente_service.cliente.domain.repository.PersonaRepo;
import org.example.cliente_service.shared.application.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarCliente<T> implements Query<List> {

    private static ClienteRepo clienteRepo;

    private ListarCliente(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public List<T> execute() {

        return clienteRepo.buscarTodos();
    }

    public static final class ListarClienteBuilder {
        private ClienteRepo clienteRepo;

        private ListarClienteBuilder() {
        }

        public static ListarClienteBuilder aListarCliente() {
            return new ListarClienteBuilder();
        }

        public ListarClienteBuilder withClienteRepo(ClienteRepo clienteRepo) {
            this.clienteRepo = clienteRepo;
            return this;
        }

        public ListarCliente build() {
            return new ListarCliente(clienteRepo);
        }
    }
}
