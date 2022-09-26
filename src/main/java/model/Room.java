package model;

import java.util.Objects;

public class Room implements IRoom{

    private String roomNumber;
    private Double price;
    private RoomType roomType;
    private RoomValue roomValue;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.roomValue = RoomValue.PAID;
    }

    public Room(String roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public Room() {
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public RoomValue getRoomValue() {
        return roomValue;
    }

    public void setRoomValue(RoomValue roomValue) {
        this.roomValue = roomValue;
    }

    @Override
    public String toString() {
        return
                "roomNumber: " + roomNumber + "| "
                + "price: " + price + "| " +
                        "roomType: " + roomType + "| "
                + "roomValue: " + roomValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber) && price.equals(room.price) && roomType == room.roomType && roomValue == room.roomValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, roomType, roomValue);
    }
}
