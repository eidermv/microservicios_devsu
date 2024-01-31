package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.mapper;

import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.CuentaEnt;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.CuentaActualizarDto;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.CuentaDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CuentaMapper {
    public CuentaEnt toEntity(CuentaDto source);
    public CuentaEnt toEntity(CuentaActualizarDto source);

    public CuentaDto toDto(CuentaEnt source);
}
