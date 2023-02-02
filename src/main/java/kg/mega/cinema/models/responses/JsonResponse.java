
package kg.mega.cinema.models.responses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class JsonResponse {

    String name;
    String image;
    String pg;
    String description;
    double rating;
    List<CinemaResponse> cinemas;

}