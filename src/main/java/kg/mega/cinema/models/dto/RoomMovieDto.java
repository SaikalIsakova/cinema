package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.entities.Movie;
import kg.mega.cinema.models.entities.Price;
import kg.mega.cinema.models.entities.Room;
import kg.mega.cinema.models.entities.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomMovieDto {
    Long id;
    RoomDto roomDto;
    MovieDto movieDto;
    ScheduleDto scheduleDto;
    PriceDto priceDto;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
