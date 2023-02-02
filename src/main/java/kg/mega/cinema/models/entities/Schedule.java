package kg.mega.cinema.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_schedule")
public class Schedule{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    boolean active;
    LocalDate dateOfFilms;
    LocalTime startTime;
    Date addDate;
    Date updateDate;


    @PrePersist
    protected void onCreate() {
        addDate=new Date();
        updateDate = new Date();
        active = true;
    }

    @PreUpdate
    protected void OnUpdate(){
        updateDate=new Date();
    }

}
