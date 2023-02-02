package kg.mega.cinema.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.mega.cinema.models.dto.RoomDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatDto{
    Long id;
    int number;
    RoomDto room;
    int row;
    boolean active;
    Date addDate;
    Date updateDate;
}
