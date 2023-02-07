package kg.mega.cinema.models.responses;

import kg.mega.cinema.models.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class PriceResponse {
    Long id;
    Double price;
    PriceType priceType;
}
