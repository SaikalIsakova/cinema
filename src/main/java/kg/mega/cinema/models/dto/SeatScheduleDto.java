package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.enums.SeatStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatScheduleDto{
    Long id;
    SeatDto seat;
    RoomMovieDto roomMovie;
    SeatStatus seatStatus;
    boolean active;
    Date addDate;
    Date updateDate;
}
