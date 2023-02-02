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
@Table(name="tb_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name="seat_schedule_id")
    Ticket ticket;

    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;

    LocalDateTime addDate;
    LocalDateTime updateDate;
    boolean active;
}
