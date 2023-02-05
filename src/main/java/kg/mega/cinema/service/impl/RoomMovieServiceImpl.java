package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomMovieRep;
import kg.mega.cinema.mappers.RoomMovieMapper;
import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.dto.ScheduleDto;
import kg.mega.cinema.models.requests.RoomMovieRequest;
import kg.mega.cinema.service.MovieService;
import kg.mega.cinema.service.RoomMovieService;
import kg.mega.cinema.service.RoomService;
import kg.mega.cinema.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomMovieServiceImpl implements RoomMovieService {
    RoomMovieMapper mapper = RoomMovieMapper.INSTANCE;

    private final RoomMovieRep rep;
    private final RoomService roomService;
    private final MovieService movieService;
    private final ScheduleService scheduleService;

    public RoomMovieServiceImpl(RoomMovieRep rep, RoomService roomService, MovieService movieService, ScheduleService scheduleService) {
        this.rep = rep;
        this.roomService = roomService;
        this.movieService = movieService;
        this.scheduleService = scheduleService;
//        this.roomMoviePriceService = roomMoviePriceService;
    }

    @Override
    public RoomMovieDto save(RoomMovieDto roomMovieDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMovieDto)));
    }

    @Override
    public RoomMovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("RoomMovie not found!")));
    }

    @Override
    public RoomMovieDto delete(Long id) {
        RoomMovieDto roomMovieDto = findById(id);
        roomMovieDto.setActive(false);
        return save(roomMovieDto);
    }

    @Override
    public List<RoomMovieDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }


    @Override
    public List<RoomMovieDto> findByRoomId(Long roomId) {

        return mapper.toDtos(rep.findByRoomId(roomId));
    }


    @Override
    public List<RoomMovieDto> getRoomMovieByMovieAndDate(Long movieId, LocalDate date) {
        return mapper.toDtos(rep.getRoomMovie(movieId,date));
    }


    @Override
    public RoomMovieDto create(RoomMovieRequest request) {
        RoomDto roomDto=roomService.findById(request.getRoomId());
        MovieDto movieDto=movieService.findById(request.getMovieId());
        ScheduleDto scheduleDto=scheduleService.findById(request.getScheduleId());

        RoomMovieDto roomMovieDto=new RoomMovieDto();
        roomMovieDto.setMovie(movieDto);
        roomMovieDto.setSchedule(scheduleDto);
        roomMovieDto.setRoom(roomDto);

        return save(roomMovieDto);
    }





}
