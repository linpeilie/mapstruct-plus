package io.github.linpeilie.me.collection.forged;

import io.github.linpeilie.annotations.AutoMapper;
import java.util.Map;
import java.util.Set;
import org.mapstruct.NullValueMappingStrategy;

@AutoMapper(target = Source1.class, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public class Target1 {

    private Set<Long> fooSet;
    private Set<Long> fooSet2;
    private Map<String, String> barMap;
    private Map<String, String> barMap2;

    private Set<Long> publicFooSet;
    private Map<String, String> publicBarMap;

    public Set<Long> getFooSet() {
        return fooSet;
    }

    public void setFooSet(Set<Long> fooSet) {
        this.fooSet = fooSet;
    }

    public Map<String, String> getBarMap() {
        return barMap;
    }

    public void setBarMap(Map<String, String> barMap) {
        this.barMap = barMap;
    }

    public Set<Long> getFooSet2() {
        return fooSet2;
    }

    public void setFooSet2( Set<Long> fooSet2 ) {
        this.fooSet2 = fooSet2;
    }

    public Map<String, String> getBarMap2() {
        return barMap2;
    }

    public void setBarMap2( Map<String, String> barMap2 ) {
        this.barMap2 = barMap2;
    }

    public Set<Long> getPublicFooSet() {
        return publicFooSet;
    }

    public void setPublicFooSet(Set<Long> publicFooSet) {
        this.publicFooSet = publicFooSet;
    }

    public Map<String, String> getPublicBarMap() {
        return publicBarMap;
    }

    public void setPublicBarMap(Map<String, String> publicBarMap) {
        this.publicBarMap = publicBarMap;
    }
}
