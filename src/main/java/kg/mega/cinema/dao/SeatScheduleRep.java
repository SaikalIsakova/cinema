package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.SeatSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SeatScheduleRep extends JpaRepository<SeatSchedule,Long> {
    @Query("select ss from SeatSchedule as ss\n" +
            "            INNER JOIN RoomMovie as rm\n" +
            "            on ss.roomMovie.id=rm.id\n" +
            "            INNER JOIN Seat as s\n" +
            "            on ss.seat.id=s.id\n" +
            "            WHERE rm.id=:roomMovieId")
    List<SeatSchedule> findByRoomMovieId(Long roomMovieId);
}
