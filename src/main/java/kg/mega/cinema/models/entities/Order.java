package kg.mega.cinema.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_order")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int price;
    LocalTime startTime;
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


