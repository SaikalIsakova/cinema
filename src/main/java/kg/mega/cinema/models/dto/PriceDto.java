package kg.mega.cinema.models.dto;

import kg.mega.cinema.models.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto{
    Long id;
    Double price;
    PriceType priceType;
    boolean active;
    Date addDate;
    Date updateDate;
}
