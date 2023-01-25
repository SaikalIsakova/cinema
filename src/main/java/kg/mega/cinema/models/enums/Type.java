package kg.mega.cinema.models.enums;

public enum Type {
    ADULT("Взрослый"),
    CHILD("Детский");

    String value;

    Type(String value) {
        this.value = value;
    }
}
