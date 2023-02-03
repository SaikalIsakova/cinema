package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRep extends JpaRepository<Seat,Long> {
    List<Seat>findByRoomId(Long roomId);
}
