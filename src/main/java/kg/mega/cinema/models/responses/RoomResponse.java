
package kg.mega.cinema.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class RoomResponse {
    String name;
    List<RoomMovieResp> seances;
}
