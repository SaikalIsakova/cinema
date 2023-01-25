package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatRep;
import kg.mega.cinema.mappers.SeatMapper;
import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRep rep;

    SeatMapper mapper=SeatMapper.INSTANCE;

    @Override
    public SeatDto save(SeatDto seatDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatDto)));
    }

    @Override
    public SeatDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("seat is not found")));
    }

    @Override
    public List<SeatDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public SeatDto delete(Long id) {

        SeatDto seatDto=findById(id);
        seatDto.setActive(false);
        return save(seatDto);
    }
}
