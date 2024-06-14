package io.github.linpeilie;

import io.github.linpeilie.annotations.MapperConfig;
import org.mapstruct.Builder;

@MapperConfig(adapterClassName = "DemoConvertMapperAdapter",
    adapterPackage = "io.github.linpeilie.adapter",
    mapAdapterClassName = "DemoMapConvertMapperAdapter",
    autoConfigPackage = "cn.easii",
    autoMapperConfigClassName = "EasiiAutoMapperConfig",
    autoMapMapperConfigClassName = "EasiiAutoMapMapperConfig",
    builder = @Builder(disableBuilder = false))
public class MapStructPlusConfiguration {
}
