package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatScheduleRep;
import kg.mega.cinema.mappers.SeatScheduleMapper;
import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.enums.SeatStatus;
import kg.mega.cinema.models.responses.Response;
import kg.mega.cinema.service.RoomMovieService;
import kg.mega.cinema.service.SeatScheduleService;
import kg.mega.cinema.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatScheduleServiceImpl implements SeatScheduleService {
    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;
    private final SeatService seatService;
    private final RoomMovieService roomMovieService;

    public SeatScheduleServiceImpl(SeatScheduleRep rep, SeatService seatService,
                                   RoomMovieService roomMovieService) {
        this.rep = rep;
        this.seatService = seatService;
        this.roomMovieService = roomMovieService;
    }

    @Override
    public SeatScheduleDto save(SeatScheduleDto seatScheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatScheduleDto)));
    }

    @Override
    public SeatScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Seat Schedule not found!")));
    }

    @Override
    public SeatScheduleDto delete(Long id) {
        SeatScheduleDto seatScheduleDto = findById(id);
        seatScheduleDto.setActive(false);
        return save(seatScheduleDto);
    }

    @Override
    public List<SeatScheduleDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }


    @Override
    public Response create(Long seanceId, List<Long> seatId) {
        RoomMovieDto roomMovieDto=roomMovieService.findById(seanceId);

        for(Long id:seatId) {

            SeatDto seatDto=seatService.findById(id);

            SeatScheduleDto seatScheduleDto=new SeatScheduleDto();
            seatScheduleDto.setRoomMovie(roomMovieDto);
            seatScheduleDto.setSeat(seatDto);
            seatScheduleDto.setSeatStatus(SeatStatus.SOLD);

            save(seatScheduleDto);
        }

        return new Response("Success");
    }
}
