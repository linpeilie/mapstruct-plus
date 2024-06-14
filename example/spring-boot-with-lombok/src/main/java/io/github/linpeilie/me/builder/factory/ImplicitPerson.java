package io.github.linpeilie.me.builder.factory;

public class ImplicitPerson {

    private final String name;
    private final String source;

    protected ImplicitPerson(ImplicitPerson.PersonBuilder builder) {
        this.name = builder.name;
        this.source = builder.source;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public static ImplicitPerson.PersonBuilder builder() {
        throw new UnsupportedOperationException( "Factory should be used" );
    }

    public static class PersonBuilder {
        private String name;
        private final String source;

        public PersonBuilder(String source) {
            this.source = source;
        }

        public ImplicitPerson.PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ImplicitPerson build() {
            return new ImplicitPerson( this );
        }
    }

}
