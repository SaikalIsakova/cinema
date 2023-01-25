package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRep extends JpaRepository<Cinema,Long> {
}
