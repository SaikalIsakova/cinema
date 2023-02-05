package kg.mega.cinema.models.responses;

import kg.mega.cinema.models.dto.SeatDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderResponse {
    String movie;
    String cinema;
    String room;
    Set<SeatResponse> seats;
    LocalDate date;
    LocalTime startTime;
    double sum;


}
