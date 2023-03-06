package kg.mega.cinema.exceptions;

public class SeatScheduleNotFoundException extends RuntimeException{
    public SeatScheduleNotFoundException(String message) {
        super(message);
    }
}
