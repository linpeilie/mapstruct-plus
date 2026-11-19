package io.github.linpeilie.compatibility;

import io.github.linpeilie.annotations.AutoMapper;

/**
 * Real processor inputs shared by the Java 8 and Java 17+ consumer tests.
 *
 * @author hb0730
 */
public final class MappingFixtures {

    private MappingFixtures() {
    }

    /** Source whose generated mapper must be discovered through the module manifest. */
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

    /** Target used to verify both forward and reverse mapping. */
    public static class Target {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
