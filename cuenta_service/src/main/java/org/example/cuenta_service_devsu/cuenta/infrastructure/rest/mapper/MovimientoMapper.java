package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.mapper;

import org.example.cuenta_service_devsu.cuenta.infrastructure.db.entity.MovimientoEnt;
import org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.request.MovimientoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovimientoMapper {
    @Mapping(source = "numero_cuenta", target = "cuenta.numero_cuenta")
    public MovimientoEnt toEntity(MovimientoDto source);
    @Mapping(source = "cuenta.numero_cuenta", target = "numero_cuenta")
    public MovimientoDto toDto(MovimientoEnt source);

}
