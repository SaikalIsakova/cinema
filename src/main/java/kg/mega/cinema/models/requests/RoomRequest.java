package kg.mega.cinema.models.requests;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomRequest {
    Long cinemaId;
    int seatCount;
    String name;
}
