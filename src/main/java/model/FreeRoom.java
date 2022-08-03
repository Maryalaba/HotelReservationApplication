package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double price, IRoom iRoom) {
        super(roomNumber, 0.0, iRoom);
    }

    @Override
    public String toString() {
        return "FreeRoom{}";
    }
}
