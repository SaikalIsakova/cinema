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
@Table(name="tb_seat")

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int numberOfSeat;
    int row;

    @ManyToOne
    @JoinColumn(name="room_id")
    Room room;

    LocalDateTime addDate;
    LocalDateTime updateTime;
    boolean active;

}
