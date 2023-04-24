package io.github.linpeilie.mapper;

import io.github.linpeilie.model.Tyre;
import io.github.linpeilie.model.TyreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TyreMapper {

    TyreDTO tyreToTyreDTO(Tyre tyre);

    Tyre tyreDtoToTyre(TyreDTO tyreDTO);

}
