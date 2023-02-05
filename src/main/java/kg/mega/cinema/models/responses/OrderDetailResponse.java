package kg.mega.cinema.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderDetailResponse {
    Long orderNumber;
    String cinema;
    String room;
    String movie;
    LocalTime starTime;
    int row;
    int number;
    double cost;
}
