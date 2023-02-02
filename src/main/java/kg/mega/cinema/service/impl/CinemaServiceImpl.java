package kg.mega.cinema.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import kg.mega.cinema.dao.CinemaRep;
import kg.mega.cinema.mappers.CinemaMapper;
import kg.mega.cinema.models.dto.CinemaDto;
import kg.mega.cinema.models.requests.SaveCinemaRequest;
import kg.mega.cinema.service.CinemaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    CinemaMapper mapper = CinemaMapper.INSTANCE;


    private final CinemaRep rep;

    public CinemaServiceImpl(CinemaRep rep) {
        this.rep = rep;
    }

    @Override
    public CinemaDto save(CinemaDto cinemaDto) {
        return mapper.toDto(rep.save(mapper.toEntity(cinemaDto)));
    }


    @Override
    public CinemaDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Cinema not found!")));
    }

    @Override
    public CinemaDto delete(Long id) {
        CinemaDto cinemaDto = findById(id);
        cinemaDto.setActive(false);
        return save(cinemaDto);
    }

    @Override
    public List<CinemaDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public CinemaDto create(SaveCinemaRequest cinema) {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setName(cinema.getName());
        cinemaDto.setAddress(cinema.getAddress());
        cinemaDto.setLogo(cinema.getLogo());
        return save(cinemaDto);
    }
}
