package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.ScheduleRep;
import kg.mega.cinema.mappers.ScheduleMapper;
import kg.mega.cinema.models.dto.ScheduleDto;
import kg.mega.cinema.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRep rep;

    ScheduleMapper mapper=ScheduleMapper.INSTANCE;

    @Override
    public ScheduleDto save(ScheduleDto scheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(scheduleDto)));
    }

    @Override
    public ScheduleDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("schedule not found")));
    }

    @Override
    public List<ScheduleDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public ScheduleDto delete(Long id) {

        ScheduleDto scheduleDto=findById(id);
        scheduleDto.setActive(false);
        return save(scheduleDto);
    }
}
