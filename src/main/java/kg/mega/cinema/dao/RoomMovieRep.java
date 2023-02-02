package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomMovieRep extends JpaRepository<MovieSession,Long> {
}
