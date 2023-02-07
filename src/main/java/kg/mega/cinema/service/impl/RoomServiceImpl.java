package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomRep;
import kg.mega.cinema.mappers.RoomMapper;
import kg.mega.cinema.models.dto.CinemaDto;
import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.requests.RoomRequest;
import kg.mega.cinema.service.CinemaService;
import kg.mega.cinema.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    RoomMapper mapper = RoomMapper.INSTANCE;

    private final RoomRep rep;
    private final CinemaService cinemaService;

    public RoomServiceImpl(RoomRep rep, CinemaService cinemaService) {
        this.rep = rep;
        this.cinemaService = cinemaService;
    }

    @Override
    public RoomDto save(RoomDto roomDto) {

        return mapper.toDto(rep.save(mapper.toEntity(roomDto)));
    }

    @Override
    public RoomDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Room not found!")));
    }

    @Override
    public RoomDto delete(Long id) {

        RoomDto roomDto = findById(id);
        roomDto.setActive(false);

        return save(roomDto);
    }

    @Override
    public List<RoomDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public RoomDto create(RoomRequest room) {

        CinemaDto cinema = cinemaService.findById(room.getCinemaId());

        RoomDto roomDto = new RoomDto();
        roomDto.setName(room.getName());
        roomDto.setSeatCount(room.getSeatCount());
        roomDto.setCinema(cinema);

        return save(roomDto);
    }

    @Override
    public RoomDto findByRoomMovieId(Long roomMovieId) {

        return mapper.toDto(rep.findByRoomMovieId(roomMovieId));
    }
}
