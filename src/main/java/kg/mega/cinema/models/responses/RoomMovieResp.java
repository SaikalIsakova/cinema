
package kg.mega.cinema.models.responses;

import kg.mega.cinema.models.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class RoomMovieResp {
    Long seanceId;
    double price;
    PriceType priceType;
    LocalTime startTime;
}
