package kg.mega.cinema.models.entities;

import kg.mega.cinema.models.enums.PriceType;
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
@Table(name = "tb_price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    @Enumerated(EnumType.STRING)
    PriceType priceType;
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
