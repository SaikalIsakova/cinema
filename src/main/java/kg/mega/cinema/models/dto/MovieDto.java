package kg.mega.cinema.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.time.LocalDateTime;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieDto {
    Long id;
    String name;
    String description;
    String image;
    double rating;
    int ageLimit;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
