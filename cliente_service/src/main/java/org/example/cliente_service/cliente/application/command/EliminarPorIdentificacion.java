package org.example.cliente_service.cliente.application.command;

import org.example.cliente_service.cliente.domain.repository.ClienteRepo;
import org.example.cliente_service.cliente.domain.repository.PersonaRepo;
import org.example.cliente_service.shared.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class EliminarPorIdentificacion implements Command<Boolean> {

    private PersonaRepo repo;
    private ClienteRepo clienteRepo;
    private int data;

    @Override
    public Boolean execute() {
        if (repo.existsById(data)) {
            repo.deleteById(data);
            //clienteRepo.eliminarPorIdentificacion(data);
            return true;
        }
        return false;
    }


    public static final class EliminarPorIdentificacionBuilder {
        private PersonaRepo repo;
        private ClienteRepo clienteRepo;
        private int data;

        private EliminarPorIdentificacionBuilder() {
        }

        public static EliminarPorIdentificacionBuilder anEliminarPorIdentificacion() {
            return new EliminarPorIdentificacionBuilder();
        }

        public EliminarPorIdentificacionBuilder withRepo(PersonaRepo repo) {
            this.repo = repo;
            return this;
        }

        public EliminarPorIdentificacionBuilder withClienteRepo(ClienteRepo clienteRepo) {
            this.clienteRepo = clienteRepo;
            return this;
        }

        public EliminarPorIdentificacionBuilder withData(int data) {
            this.data = data;
            return this;
        }

        public EliminarPorIdentificacion build() {
            EliminarPorIdentificacion eliminarPorIdentificacion = new EliminarPorIdentificacion();
            eliminarPorIdentificacion.repo = this.repo;
            eliminarPorIdentificacion.clienteRepo = this.clienteRepo;
            eliminarPorIdentificacion.data = this.data;
            return eliminarPorIdentificacion;
        }
    }
}
