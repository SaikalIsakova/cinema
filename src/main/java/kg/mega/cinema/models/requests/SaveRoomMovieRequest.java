package kg.mega.cinema.models.requests;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveRoomMovieRequest {
    Long roomId;
    Long movieId;
    Long scheduleId;

}
