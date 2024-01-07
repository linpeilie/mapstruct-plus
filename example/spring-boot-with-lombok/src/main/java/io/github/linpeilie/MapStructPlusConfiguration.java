package io.github.linpeilie;

import io.github.linpeilie.annotations.MapperConfig;

@MapperConfig(adapterClassName = "DemoConvertMapperAdapter",
    adapterPackage = "io.github.linpeilie.adapter",
    mapAdapterClassName = "DemoMapConvertMapperAdapter",
    autoConfigPackage = "cn.easii",
    autoMapperConfigClassName = "EasiiAutoMapperConfig",
    autoMapMapperConfigClassName = "EasiiAutoMapMapperConfig")
public class MapStructPlusConfiguration {
}
