package model;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double");

    private String display;

    RoomType(String display) {
        this.display = display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
