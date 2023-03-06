package kg.mega.cinema.exceptions;

public class RoomMovieNotFoundException extends RuntimeException{
    public RoomMovieNotFoundException(String message) {
        super(message);
    }
}
