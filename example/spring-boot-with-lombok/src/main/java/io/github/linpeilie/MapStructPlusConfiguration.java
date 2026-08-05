package io.github.linpeilie;

import io.github.linpeilie.annotations.MapperConfig;
import io.github.linpeilie.converter.CustomConverter;
import org.mapstruct.Builder;

@MapperConfig(adapterClassName = "DemoConvertMapperAdapter",
    adapterPackage = "io.github.linpeilie.adapter",
    mapAdapterClassName = "DemoMapConvertMapperAdapter",
    autoConfigPackage = "cn.easii",
    autoMapperConfigClassName = "EasiiAutoMapperConfig",
    autoMapMapperConfigClassName = "EasiiAutoMapMapperConfig",
    mapObjectConverter = CustomConverter.class,
    builder = @Builder(disableBuilder = false))
public class MapStructPlusConfiguration {
}
