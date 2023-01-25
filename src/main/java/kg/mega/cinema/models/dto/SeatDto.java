package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.entities.Room;
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
public class SeatDto {
    Long id;
    int numberOfSeat;
    int row;
    RoomDto roomDto;
    LocalDateTime addDate;
    LocalDateTime updateTime;
    boolean active;
}
