package kg.mega.cinema.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="tb_room_movie")
public class RoomMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name="room_id")
    Room room;


    @ManyToOne
    @JoinColumn(name="movie_id")
    Movie movie;


    @ManyToOne
    @JoinColumn(name="schedule_id")
    Schedule schedule;


    @ManyToOne
    @JoinColumn(name="price_id")
    Price price;

    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
