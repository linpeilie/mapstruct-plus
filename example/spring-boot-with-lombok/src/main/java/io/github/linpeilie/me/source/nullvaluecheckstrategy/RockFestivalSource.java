package io.github.linpeilie.me.source.nullvaluecheckstrategy;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.github.linpeilie.annotations.AutoMapping;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import static org.mapstruct.NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;

@AutoMappers({
    @AutoMapper(
        target = RockFestivalTarget.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        reverseConvertGenerate = false
    ),
    @AutoMapper(
        target = RockFestivalTarget1.class,
        nullValueCheckStrategy = ON_IMPLICIT_CONVERSION,
        reverseConvertGenerate = false
    ),
})
public class RockFestivalSource {

    @AutoMapping( target = "stage", source = "artistName" )
    private String artistName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}
