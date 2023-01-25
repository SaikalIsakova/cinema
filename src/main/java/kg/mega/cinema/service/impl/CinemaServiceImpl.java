package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.CinemaRep;
import kg.mega.cinema.mappers.CinemaMapper;
import kg.mega.cinema.models.dto.CinemaDto;
import kg.mega.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    CinemaRep rep;

    CinemaMapper mapper=CinemaMapper.INSTANCE;

    @Override
    public CinemaDto save(CinemaDto cinemaDto) {
        return mapper.toDto(rep.save(mapper.toEntity(cinemaDto)));
    }

    @Override
    public CinemaDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Кинотеатр не найден")));
    }

    @Override
    public List<CinemaDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public CinemaDto delete(Long id) {
        CinemaDto cinemaDto = findById(id);
        cinemaDto.setActive(false);
        return save(cinemaDto);
    }
}
