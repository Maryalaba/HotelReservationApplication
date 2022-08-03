package model;

public class Room implements IRoom{

    private String roomNumber;
    private Double price;
    private IRoom iRoom;

    public Room(String roomNumber, Double price, IRoom iRoom) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.iRoom = iRoom;
    }

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", iRoom=" + iRoom +
                '}';
    }
}
