package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.RoomMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomMovieRep extends JpaRepository<RoomMovie,Long> {

    List<RoomMovie> findByRoomId(Long roomId);

    @Query("select r from RoomMovie r " +
            "inner join Schedule s on r.schedule.id=s.id " +
            "where r.movie.id=:movieId " +
            "and s.dateOfFilms=:date")
    List<RoomMovie> getRoomMovie(Long movieId, LocalDate date);

}