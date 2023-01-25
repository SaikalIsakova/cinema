package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRep extends JpaRepository<Schedule,Long> {
}
