package entity;

import java.lang.reflect.Type;

public class CarType {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public CarType setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
