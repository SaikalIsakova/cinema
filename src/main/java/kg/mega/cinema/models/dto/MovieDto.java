package kg.mega.cinema.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {

    Long id;
    String definition;
    String name;
    Double rating;
    String pg;
    boolean active;
    String image;
    Date addDate;
    Date updateDate;

}
