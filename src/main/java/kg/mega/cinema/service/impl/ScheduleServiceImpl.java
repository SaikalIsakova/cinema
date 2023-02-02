package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.ScheduleRep;
import kg.mega.cinema.mappers.ScheduleMapper;
import kg.mega.cinema.models.dto.ScheduleDto;
import kg.mega.cinema.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    ScheduleMapper mapper = ScheduleMapper.INSTANCE;
    private final ScheduleRep rep;

    public ScheduleServiceImpl(ScheduleRep rep) {
        this.rep = rep;
    }

    @Override
    public ScheduleDto save(ScheduleDto scheduleDto) {

        return mapper.toDto(rep.save(mapper.toEntity(scheduleDto)));
    }

    @Override
    public ScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Schedule not found!")));
    }

    @Override
    public ScheduleDto delete(Long id) {
        ScheduleDto scheduleDto = findById(id);
        scheduleDto.setActive(false);
        return save(scheduleDto);
    }

    @Override
    public List<ScheduleDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public ScheduleDto create(LocalDate dateOfFilm, LocalTime startTime) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setDateOfFilms(dateOfFilm);
        scheduleDto.setStartTime(startTime);

        return save(scheduleDto);
    }
}
