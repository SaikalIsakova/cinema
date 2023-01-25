package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRep extends JpaRepository<Seat,Long> {
}
