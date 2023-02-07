package kg.mega.cinema.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class MovieResponse {

    Long id;
    String definition;
    String name;
    Double rating;
    String pg;
    String image;

}
