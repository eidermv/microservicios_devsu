package org.example.cliente_service.cliente.infrastructure.rest.mapper;

import org.example.cliente_service.cliente.infrastructure.db.entity.ClienteEnt;
import org.example.cliente_service.cliente.infrastructure.db.entity.PersonaEnt;
import org.example.cliente_service.cliente.infrastructure.rest.dto.request.PersonaClienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    public ClienteEnt toEntity(PersonaClienteDto source);

    public PersonaClienteDto toDto(ClienteEnt clienteEnt);
}
