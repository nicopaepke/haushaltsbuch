package npaepke.haushaltsbuch.services.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import npaepke.haushaltsbuch.models.Purpose;
import npaepke.haushaltsbuch.services.dtos.PurposeDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PurposeMapper {

    PurposeDTO convertPurpose(Purpose purpose);

    List<PurposeDTO> convertPurposes(List<Purpose> purposes);

    @Mappings(@Mapping(target = "parent", ignore = true))
    Purpose convertPurpose(PurposeDTO purpose);

}
