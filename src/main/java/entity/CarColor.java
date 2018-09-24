package entity;

public enum CarColor {
    RED("red"),
    WHITE("white"),
    BLUE("blue"),
    BLACK("black"),
    DEFAULT("none");

    private String color;

    CarColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public CarColor getByColor(String color) {
        for (CarColor carColor : CarColor.values()) {
            if (color.equals(carColor.getColor())) {
                return carColor;
            }
        }
        return DEFAULT;
    }
}
