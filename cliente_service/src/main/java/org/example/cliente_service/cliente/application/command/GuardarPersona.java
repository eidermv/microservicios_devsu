package org.example.cliente_service.cliente.application.command;

import org.example.cliente_service.cliente.domain.repository.PersonaRepo;
import org.example.cliente_service.shared.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class GuardarPersona<T> implements Command<T> {

    private PersonaRepo repo;
    private T data;

    @Override
    public T execute() {
        return (T) repo.save(data);
    }

    private GuardarPersona() {
    }


    public static final class GuardarPersonaBuilder<T> {
        private PersonaRepo repo;
        private T data;

        private GuardarPersonaBuilder() {
        }

        public static GuardarPersonaBuilder aGuardarPersona() {
            return new GuardarPersonaBuilder();
        }

        public GuardarPersonaBuilder withRepo(PersonaRepo repo) {
            this.repo = repo;
            return this;
        }

        public GuardarPersonaBuilder withData(T data) {
            this.data = data;
            return this;
        }

        public GuardarPersona build() {
            GuardarPersona guardarPersona = new GuardarPersona();
            guardarPersona.data = this.data;
            guardarPersona.repo = this.repo;
            return guardarPersona;
        }
    }
}
