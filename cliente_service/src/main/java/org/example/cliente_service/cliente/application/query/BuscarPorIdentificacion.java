package org.example.cliente_service.cliente.application.query;

import org.example.cliente_service.cliente.domain.repository.ClienteRepo;
import org.example.cliente_service.shared.application.query.Query;
import org.springframework.stereotype.Component;

@Component
public class BuscarPorIdentificacion<T> implements Query<T> {

    private ClienteRepo repo;
    private int data;

    private BuscarPorIdentificacion() {
    }

    @Override
    public T execute() {
        return (T) repo.buscarPorIdentificacion(data);
    }


    public static final class BuscarPorIdentificacionBuilder {
        private ClienteRepo repo;
        private int data;

        private BuscarPorIdentificacionBuilder() {
        }

        public static BuscarPorIdentificacionBuilder aBuscarPorIdentificacion() {
            return new BuscarPorIdentificacionBuilder();
        }

        public BuscarPorIdentificacionBuilder withRepo(ClienteRepo repo) {
            this.repo = repo;
            return this;
        }

        public BuscarPorIdentificacionBuilder withData(int data) {
            this.data = data;
            return this;
        }

        public BuscarPorIdentificacion build() {
            BuscarPorIdentificacion buscarPorIdentificacion = new BuscarPorIdentificacion();
            buscarPorIdentificacion.data = this.data;
            buscarPorIdentificacion.repo = this.repo;
            return buscarPorIdentificacion;
        }
    }
}
