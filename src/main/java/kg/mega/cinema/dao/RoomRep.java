package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRep extends JpaRepository<Room,Long> {
}
