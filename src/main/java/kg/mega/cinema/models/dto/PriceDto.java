package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.enums.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {
    Long id;
    double price;
    Type type;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
