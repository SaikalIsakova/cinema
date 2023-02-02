package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SeatScheduleRep extends JpaRepository<Ticket,Long> {
}
