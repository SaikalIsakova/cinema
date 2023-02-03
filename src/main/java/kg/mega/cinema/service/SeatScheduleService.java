package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.responses.Response;
import kg.mega.cinema.models.responses.SeatScheduleResponse;

import java.util.List;

public interface SeatScheduleService extends BaseService<SeatScheduleDto>{
    Response create(Long seanceId, List<Long>seatId);
    List<SeatScheduleDto>findByRoomMovieId(Long roomMovieId);
    List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId);

}
