package kg.mega.cinema.models.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMoviePriceRequest {
    Long priceId;
    Long roomMovieId;

}
