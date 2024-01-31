package org.example.cliente_service.shared.application.command;

public interface Command<T> {
   public T execute();
}
