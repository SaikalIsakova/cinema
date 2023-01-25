package kg.mega.cinema.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="tb_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int seatCount;

    @ManyToOne
    @JoinColumn(name="cinema_id")
    Cinema cinema;

    LocalDateTime addTime;
    LocalDateTime updateTime;
    boolean active;


}
