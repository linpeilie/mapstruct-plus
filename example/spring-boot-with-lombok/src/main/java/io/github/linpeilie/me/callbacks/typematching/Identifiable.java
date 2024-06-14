package io.github.linpeilie.me.callbacks.typematching;

public abstract class Identifiable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
