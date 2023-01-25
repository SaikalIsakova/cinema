package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomMovieRep;
import kg.mega.cinema.mappers.RoomMovieMapper;
import kg.mega.cinema.models.dto.RoomMovieDto;
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
    public RoomMovieDto save(RoomMovieDto roomMovieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMovieDto)));
    }

    @Override
    public RoomMovieDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Room-movie is not found")));
    }

    @Override
    public List<RoomMovieDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public RoomMovieDto delete(Long id) {

        RoomMovieDto roomMovieDto=findById(id);
        roomMovieDto.setActive(false);

        return save(roomMovieDto);
    }
}
