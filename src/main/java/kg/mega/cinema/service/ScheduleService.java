package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.ScheduleDto;
import kg.mega.cinema.models.responses.ScheduleResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService extends BaseService<ScheduleDto>{
    ScheduleDto create(LocalDate dateOfFilm, LocalTime startTime);
    List<ScheduleResponse>findAllSchedules();
}
