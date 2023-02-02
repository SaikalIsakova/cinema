package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketDto {

    Long id;
    SeatDto seat;
    MovieSessionDto roomMovie;
    Status status;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
