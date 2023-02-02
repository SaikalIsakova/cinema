package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatScheduleRep;
import kg.mega.cinema.mappers.SeatScheduleMapper;
import kg.mega.cinema.models.dto.TicketDto;
import kg.mega.cinema.service.SeatScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatScheduleServiceImpl implements SeatScheduleService {

    @Autowired
    SeatScheduleRep rep;

    SeatScheduleMapper mapper=SeatScheduleMapper.INSTANCE;

    @Override
    public TicketDto save(TicketDto ticketDto) {
        return mapper.toDto(rep.save(mapper.toEntity(ticketDto)));
    }

    @Override
    public TicketDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("seat-schedule not found")));
    }

    @Override
    public List<TicketDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public TicketDto delete(Long id) {

        TicketDto scheduleDto=findById(id);
        scheduleDto.setActive(false);
        return save(scheduleDto);
    }
}
