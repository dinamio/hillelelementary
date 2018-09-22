package entity;

public class Car {

    private long id;
    private CarType type;
    private String name;
    private CarColor color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name + " is " + type.getName() + " and have " + color.name().toLowerCase() + " color.";
    }
}
