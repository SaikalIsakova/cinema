package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.entities.Cinema;
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
public class RoomDto {
    Long id;
    int seatCount;
    CinemaDto cinema;
    LocalDateTime addTime;
    LocalDateTime updateTime;
    boolean active;
}
