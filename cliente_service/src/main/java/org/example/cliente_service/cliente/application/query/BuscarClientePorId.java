package org.example.cliente_service.cliente.application.query;

import org.example.cliente_service.cliente.domain.repository.ClienteRepo;
import org.example.cliente_service.shared.application.query.Query;
import org.springframework.stereotype.Component;

@Component
public class BuscarClientePorId<T> implements Query<T> {

    private ClienteRepo repo;
    private int data;

    private BuscarClientePorId() {
    }

    @Override
    public T execute() {
        return (T) repo.buscarPorId(data);
    }


    public static final class BuscarClientePorIdBuilder {
        private ClienteRepo repo;
        private int data;

        private BuscarClientePorIdBuilder() {
        }

        public static BuscarClientePorIdBuilder aBuscarClientePorId() {
            return new BuscarClientePorIdBuilder();
        }

        public BuscarClientePorIdBuilder withRepo(ClienteRepo repo) {
            this.repo = repo;
            return this;
        }

        public BuscarClientePorIdBuilder withData(int data) {
            this.data = data;
            return this;
        }

        public BuscarClientePorId build() {
            BuscarClientePorId buscarClientePorId = new BuscarClientePorId();
            buscarClientePorId.repo = this.repo;
            buscarClientePorId.data = this.data;
            return buscarClientePorId;
        }
    }
}
