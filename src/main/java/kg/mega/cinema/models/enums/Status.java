package kg.mega.cinema.models.enums;

public enum Status {
    FREE("Свободен"),
    BOOKED("Забронирован"),
    SOLD("Продан");

    String value;

    Status(String value) {
        this.value = value;
    }
}
