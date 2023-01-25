package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.entities.RoomMovie;
import kg.mega.cinema.models.entities.Seat;
import kg.mega.cinema.models.enums.Status;
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
public class SeatScheduleDto {

    Long id;
    SeatDto seatDto;
    RoomMovieDto roomMovieDto;
    Status status;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
