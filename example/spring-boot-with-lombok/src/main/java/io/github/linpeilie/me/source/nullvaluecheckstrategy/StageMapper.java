package io.github.linpeilie.me.source.nullvaluecheckstrategy;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StageMapper {
    default Stage artistToStage(String name) {
        return Stage.forArtist(name);
    }
}
