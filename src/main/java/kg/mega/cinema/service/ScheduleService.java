package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleService extends BaseService<ScheduleDto>{
    ScheduleDto create(LocalDate dateOfFilm, LocalTime startTime);
}
