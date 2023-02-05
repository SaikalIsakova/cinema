package kg.mega.cinema.models.entities;

import kg.mega.cinema.models.enums.SeatStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_seat_schedule")
public class SeatSchedule{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Seat seat;
    @ManyToOne
    RoomMoviePrice roomMoviePrice;
    @Enumerated(EnumType.STRING)
    SeatStatus seatStatus;
    boolean active;
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
