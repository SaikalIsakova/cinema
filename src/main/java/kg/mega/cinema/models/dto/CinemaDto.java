package kg.mega.cinema.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.time.LocalDateTime;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaDto {
    Long id;
    String name;
    String address;
    String image;
    @JsonIgnore
    LocalDateTime addDate;
    @JsonIgnore
    LocalDateTime updateDate;
    @JsonIgnore
    boolean active;
}
