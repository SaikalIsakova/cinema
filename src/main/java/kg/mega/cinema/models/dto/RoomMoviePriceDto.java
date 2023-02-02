package kg.mega.cinema.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMoviePriceDto {
    Long id;
    PriceDto price;
    RoomMovieDto roomMovie;
    boolean active;
    Date addDate;
    Date updateDate;
}
