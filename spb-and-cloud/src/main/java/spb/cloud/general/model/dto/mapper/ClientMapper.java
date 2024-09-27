package spb.cloud.general.model.dto.mapper;

import spb.cloud.general.model.dto.ClientDto;
import spb.cloud.general.model.entity.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ClientMapper {

    Client toEntity(ClientDto clientDto);

    ClientDto toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(
        Client clientDto, @MappingTarget Client client);
}