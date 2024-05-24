/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.collection;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AutoMapper(target = Target.class, uses = SourceTargetMapper.class)
public class Source {

    private List<String> publicStringList;

    private List<String> stringList;
    private List<String> otherStringList;
    private ArrayList<String> stringArrayList;

    private Set<String> stringSet;
    private HashSet<String> stringHashSet;

    private Collection<String> stringCollection;

    @AutoMapping(target = "integerCollection")
    private List<Integer> integerList;

    @AutoMapping(target = "set")
    private Set<Integer> integerSet;

    @AutoMapping(target = "anotherStringSet")
    private Set<Integer> anotherIntegerSet;

    private Set<Colour> colours;

    private Map<String, Long> stringLongMap;

    private Map<String, Long> otherStringLongMap;

    @AutoMapping(target = "nonGenericMapStringtoLong")
    private Map<String, Long> stringLongMapForNonGeneric;

    @AutoMapping(target = "stringListNoSetter")
    private List<String> stringList2;

    @AutoMapping(target = "stringListNoSetter2")
    private Set<String> stringSet2;

    private EnumSet<Colour> enumSet;

    @AutoMapping(target = "nonGenericStringList")
    private List<String> stringList3;

    public List<String> getPublicStringList() {
        return publicStringList;
    }

    public void setPublicStringList(List<String> publicStringList) {
        this.publicStringList = publicStringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public HashSet<String> getStringHashSet() {
        return stringHashSet;
    }

    public void setStringHashSet(HashSet<String> stringHashSet) {
        this.stringHashSet = stringHashSet;
    }

    public Collection<String> getStringCollection() {
        return stringCollection;
    }

    public void setStringCollection(Collection<String> stringCollection) {
        this.stringCollection = stringCollection;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public Set<Integer> getIntegerSet() {
        return integerSet;
    }

    public void setIntegerSet(Set<Integer> integerSet) {
        this.integerSet = integerSet;
    }

    public Set<Integer> getAnotherIntegerSet() {
        return anotherIntegerSet;
    }

    public void setAnotherIntegerSet(Set<Integer> anotherIntegerSet) {
        this.anotherIntegerSet = anotherIntegerSet;
    }

    public Set<Colour> getColours() {
        return colours;
    }

    public void setColours(Set<Colour> colours) {
        this.colours = colours;
    }

    public Map<String, Long> getStringLongMap() {
        return stringLongMap;
    }

    public void setStringLongMap(Map<String, Long> stringLongMap) {
        this.stringLongMap = stringLongMap;
    }

    public List<String> getStringList2() {
        return stringList2;
    }

    public void setStringList2(List<String> stringList2) {
        this.stringList2 = stringList2;
    }

    public List<String> getOtherStringList() {
        return otherStringList;
    }

    public void setOtherStringList(List<String> otherStringList) {
        this.otherStringList = otherStringList;
    }

    public Map<String, Long> getOtherStringLongMap() {
        return otherStringLongMap;
    }

    public void setOtherStringLongMap(Map<String, Long> otherStringLongMap) {
        this.otherStringLongMap = otherStringLongMap;
    }

    public Set<String> getStringSet2() {
        return stringSet2;
    }

    public void setStringSet2(Set<String> stringSet2) {
        this.stringSet2 = stringSet2;
    }

    public EnumSet<Colour> getEnumSet() {
        return enumSet;
    }

    public void setEnumSet(EnumSet<Colour> enumSet) {
        this.enumSet = enumSet;
    }

    public List<String> getStringList3() {
        return stringList3;
    }

    public void setStringList3(List<String> stringList3) {
        this.stringList3 = stringList3;
    }

    public Map<String, Long> getStringLongMapForNonGeneric() {
        return stringLongMapForNonGeneric;
    }

    public void setStringLongMapForNonGeneric(Map<String, Long> stringLongMapForNonGeneric) {
        this.stringLongMapForNonGeneric = stringLongMapForNonGeneric;
    }
}
