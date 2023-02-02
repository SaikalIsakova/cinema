package kg.mega.cinema.models.requests;

import kg.mega.cinema.models.enums.PriceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavePriceRequest {
    Double price;
    PriceType priceType;
}
