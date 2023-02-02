package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.responses.Response;

import java.util.List;

public interface SeatScheduleService extends BaseService<SeatScheduleDto>{
    Response create(Long seanceId, List<Long>seatId);

}
