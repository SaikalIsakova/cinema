package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatScheduleRep;
import kg.mega.cinema.mappers.SeatScheduleMapper;
import kg.mega.cinema.models.dto.*;
import kg.mega.cinema.models.enums.SeatStatus;
import kg.mega.cinema.models.responses.Response;
import kg.mega.cinema.models.responses.SeatScheduleResponse;
import kg.mega.cinema.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatScheduleServiceImpl implements SeatScheduleService {
    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;
    private final SeatService seatService;
    private final RoomMoviePriceService roomMoviePriceService;
    private final RoomService roomService;

    public SeatScheduleServiceImpl(SeatScheduleRep rep, SeatService seatService,
                                   RoomMoviePriceService roomMoviePriceService, RoomService roomService) {
        this.rep = rep;
        this.seatService = seatService;
        this.roomMoviePriceService = roomMoviePriceService;
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
        RoomMoviePriceDto roomMoviePriceDto = roomMoviePriceService.findById(seanceId);

        for (Long id : seatId) {

            SeatDto seatDto = seatService.findById(id);

            SeatScheduleDto seatScheduleDto = new SeatScheduleDto();
            seatScheduleDto.setRoomMoviePrice(roomMoviePriceDto);
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
        //тут мы находим какие места куплены = их у меня 2
        List<SeatScheduleDto> seatScheduleList = findByRoomMovieId(roomMovieId);
        //тут мы находим зал по сеансу
        RoomDto roomDto = roomService.findByRoomMovieId(roomMovieId);
        //тут находим места по залу = их у меня 20
        List<SeatDto> seatList = seatService.findByRoomId(roomDto.getId());
        //это лист который надо вывести.
        List<SeatScheduleResponse> seatScheduleResList = new ArrayList<>();


        for (SeatDto seatItem:seatList){ //20 мест
            SeatScheduleResponse response=new SeatScheduleResponse();

            for(SeatScheduleDto seatScheduleItem:seatScheduleList){//2 места (1-2-айди)
                SeatScheduleDto seatScheduleDto= seatScheduleItem;//тут в объект передаю значения этого листа,что бы можно было сравнить

                if(seatItem.getId().equals(seatScheduleDto.getSeat().getId())){//если == то вытягиваем данные из этого объекта
                    response.setId(seatScheduleDto.getSeat().getId());
                    response.setRow(seatScheduleDto.getSeat().getRow());
                    response.setSeatNum(seatScheduleDto.getSeat().getNumber());
                    response.setStatus(seatScheduleDto.getSeatStatus());
                    break;

                }else{
                    response.setId(seatItem.getId());//если не совпадает вытягиваем данные seat
                    response.setRow(seatItem.getRow());
                    response.setSeatNum(seatItem.getNumber());
                    response.setStatus(SeatStatus.FREE);
                }
            }
            seatScheduleResList.add(response);
        }
        return seatScheduleResList;
    }


}


