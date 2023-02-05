package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.SeatSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SeatScheduleRep extends JpaRepository<SeatSchedule,Long> {
    @Query("select ss from SeatSchedule ss " +
            "inner join RoomMoviePrice rmp " +
            "on ss.roomMoviePrice.id=rmp.id " +
            "inner join RoomMovie rm " +
            "on rmp.roomMovie.id=rm.id " +
            "where rm.id=:roomMovieId" )
    List<SeatSchedule> findByRoomMovieId(Long roomMovieId);

}
