package io.github.linpeilie.mapstruct.autoconfigure.fixture;

import io.github.linpeilie.BaseMapper;
import io.github.linpeilie.annotations.AutoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * Real annotation-processor inputs for conversion and Spring dependency-injection tests.
 *
 * @author hb0730
 */
public final class Boot4MappingFixtures {

    private Boot4MappingFixtures() {
    }

    /** Source whose forward and reverse mappers must be registered without component scanning. */
    @AutoMapper(target = Target.class)
    public static class Source {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /** Target for the generated bidirectional mapping. */
    public static class Target {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /** Input for a custom mapper with an application-provided collaborator. */
    public static class InjectedSource {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /** Output that makes use of the injected collaborator observable. */
    public static class InjectedTarget {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /** Application bean that the generated mapper must obtain from Spring. */
    public static class PrefixFormatter {

        @Named("prefix")
        public String prefix(String name) {
            return "boot4:" + name;
        }
    }

    /** Custom Spring mapper exercising manifest registration and the generated autowired field. */
    @Mapper(componentModel = "spring-lazy", uses = PrefixFormatter.class)
    public interface InjectedMapper extends BaseMapper<InjectedSource, InjectedTarget> {

        @Override
        @Mapping(target = "name", source = "name", qualifiedByName = "prefix")
        InjectedTarget convert(InjectedSource source);

        @Override
        @Mapping(target = "name", source = "name", qualifiedByName = "prefix")
        InjectedTarget convert(InjectedSource source, @MappingTarget InjectedTarget target);
    }
}
