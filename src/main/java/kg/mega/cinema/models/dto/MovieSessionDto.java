package kg.mega.cinema.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieSessionDto {
    Long id;
    RoomDto room;
    MovieDto movie;
    ScheduleDto schedule;
    PriceDto price;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
