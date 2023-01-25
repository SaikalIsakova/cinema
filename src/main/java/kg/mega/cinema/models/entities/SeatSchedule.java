package kg.mega.cinema.models.entities;

import kg.mega.cinema.models.enums.Status;
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
@Table(name="tb_seat_schedule")
public class SeatSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name ="seat_id")
    Seat seat;


    @ManyToOne
    @JoinColumn(name="room_movie_id")
    RoomMovie roomMovie;

    Status status;
    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
