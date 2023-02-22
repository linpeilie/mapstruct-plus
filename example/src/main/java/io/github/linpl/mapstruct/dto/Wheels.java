package io.github.linpl.mapstruct.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Wheels implements Iterable<Wheel> {
    private List<Wheel> wheelsList = new ArrayList<>();

    public List<Wheel> getWheelsList() {
        return wheelsList;
    }

    public void setWheelsList(List<Wheel> wheelsList) {
        this.wheelsList = wheelsList;
    }

    public void add(final Wheel wheel) {
        wheelsList.add(wheel);
    }

    @Override
    public Iterator<Wheel> iterator() {
        return getWheelsList().iterator();
    }

    @Override
    public void forEach(Consumer<? super Wheel> action) {
        getWheelsList().forEach(action);
    }

    @Override
    public Spliterator<Wheel> spliterator() {
        return getWheelsList().spliterator();
    }
}
