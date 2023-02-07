package kg.mega.cinema.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ScheduleResponse {
    Long id;
    LocalDate date;
    LocalTime startTime;
}
