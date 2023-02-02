package kg.mega.cinema.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto{
    Long id;
    CinemaDto cinema;
    String name;
    int seatCount;
    boolean active;
    Date addDate;
    Date updateDate;
}
