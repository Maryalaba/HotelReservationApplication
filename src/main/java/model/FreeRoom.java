package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, roomType);
        this.setPrice(0.0);
        this.setRoomValue(RoomValue.FREE);
    }

    @Override
    public String toString() {
        return
                "roomNumber: " + getRoomNumber() + "| "
                        + "price: " + getRoomPrice() + "| " +
                        "roomType: " + getRoomType() + "| "
                        + "roomValue: " + getRoomValue();
    }
}
