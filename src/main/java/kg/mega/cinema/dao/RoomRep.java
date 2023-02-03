package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRep extends JpaRepository<Room,Long> {
    @Query("Select r from Room r " +
            "inner join RoomMovie rm on r.id=rm.room.id \n" +
            "where rm.id=:roomMovieId")
    Room findByRoomMovieId(Long roomMovieId);
}
