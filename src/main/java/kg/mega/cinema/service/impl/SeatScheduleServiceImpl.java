package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatScheduleRep;
import kg.mega.cinema.mappers.SeatScheduleMapper;
import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.enums.SeatStatus;
import kg.mega.cinema.models.responses.Response;
import kg.mega.cinema.models.responses.SeatScheduleResponse;
import kg.mega.cinema.service.RoomMovieService;
import kg.mega.cinema.service.RoomService;
import kg.mega.cinema.service.SeatScheduleService;
import kg.mega.cinema.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatScheduleServiceImpl implements SeatScheduleService {
    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;
    private final SeatService seatService;
    private final RoomMovieService roomMovieService;
    private final RoomService roomService;

    public SeatScheduleServiceImpl(SeatScheduleRep rep, SeatService seatService,
                                   RoomMovieService roomMovieService, RoomService roomService) {
        this.rep = rep;
        this.seatService = seatService;
        this.roomMovieService = roomMovieService;
        this.roomService = roomService;

    }

    @Override
    public SeatScheduleDto save(SeatScheduleDto seatScheduleDto) {
        return mapper.toDto(rep.save(mapper.toEntity(seatScheduleDto)));
    }

    @Override
    public SeatScheduleDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Seat Schedule not found!")));
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
        RoomMovieDto roomMovieDto = roomMovieService.findById(seanceId);

        for (Long id : seatId) {

            SeatDto seatDto = seatService.findById(id);

            SeatScheduleDto seatScheduleDto = new SeatScheduleDto();
            seatScheduleDto.setRoomMovie(roomMovieDto);
            seatScheduleDto.setSeat(seatDto);
            seatScheduleDto.setSeatStatus(SeatStatus.SOLD);

            save(seatScheduleDto);
        }

        return new Response("Success");
    }

    @Override
    public List<SeatScheduleDto> findByRoomMovieId(Long roomMovieId) {

        return mapper.toDtos(rep.findByRoomMovieId(roomMovieId));
    }

    @Override
    public List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId) {
        //тут мы находим какие места куплены = их у меня 3
        List<SeatScheduleDto> seatScheduleList = findByRoomMovieId(roomMovieId);
        //тут мы находим зал по сеансу
        RoomDto roomDto = roomService.findByRoomMovieId(roomMovieId);
        //тут находим места по залу = их у меня 20
        List<SeatDto> seatList = seatService.findByRoomId(roomDto.getId());
        //это лист который надо вывести.
        List<SeatScheduleResponse> seatScheduleResList = new ArrayList<>();

        SeatScheduleResponse seatScheduleResponse = new SeatScheduleResponse();

        for (SeatScheduleDto item : seatScheduleList) { // размер 3
            for (SeatDto item2 : seatList) { //размер 20

                if (item2.getId().equals(item.getSeat().getId())) {
                    for (SeatScheduleResponse newItem : seatScheduleResList) {
//                        if (newItem.getSeatScheduleId())
                        //если купленные места и айди мест совпадает то должны сохр как уже купленные
                        seatScheduleResponse.setStatus(item.getSeatStatus());//тут статус солд
                        seatScheduleResponse.setSeatScheduleId(item.getId());
                        seatScheduleResponse.setSeatNum(item.getSeat().getNumber());
                        seatScheduleResponse.setRow(item.getSeat().getRow());
                        seatScheduleResList.add(seatScheduleResponse);
                    }
                }
                else{
                        //если айдишки не совпадают то free
                        seatScheduleResponse.setSeatScheduleId(item2.getId());
                        seatScheduleResponse.setSeatNum(item2.getNumber());
                        seatScheduleResponse.setRow(item2.getRow());
                        seatScheduleResponse.setStatus(SeatStatus.FREE);
                        seatScheduleResList.add(seatScheduleResponse);
                    }

                }

            }
            return seatScheduleResList;
        }

    }


