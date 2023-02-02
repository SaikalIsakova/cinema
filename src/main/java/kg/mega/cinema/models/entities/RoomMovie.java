package kg.mega.cinema.models.entities;

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
@Table(name = "tb_room_movie")
public class RoomMovie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Room room;
    @ManyToOne
    Movie movie;
    @ManyToOne
    Schedule schedule;
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
