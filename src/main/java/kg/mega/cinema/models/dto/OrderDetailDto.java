package kg.mega.cinema.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailDto {
    Long id;
    TicketDto seatSchedule;
    OrderDto order;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
