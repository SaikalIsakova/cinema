package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomMovieRep;
import kg.mega.cinema.mappers.RoomMovieMapper;
import kg.mega.cinema.models.dto.MovieSessionDto;
import kg.mega.cinema.service.RoomMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomMovieServiceImpl implements RoomMovieService {

    @Autowired
    RoomMovieRep rep;

    RoomMovieMapper mapper=RoomMovieMapper.INSTANCE;

    @Override
    public MovieSessionDto save(MovieSessionDto movieSessionDto) {
        return mapper.toDto(rep.save(mapper.toEntity(movieSessionDto)));
    }

    @Override
    public MovieSessionDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Room-movie is not found")));
    }

    @Override
    public List<MovieSessionDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public MovieSessionDto delete(Long id) {

        MovieSessionDto movieSessionDto =findById(id);
        movieSessionDto.setActive(false);

        return save(movieSessionDto);
    }
}
