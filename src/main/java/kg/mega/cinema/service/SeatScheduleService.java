package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.enums.PriceType;
import kg.mega.cinema.models.responses.OrderDetailResponse;
import kg.mega.cinema.models.responses.SeatScheduleResponse;

import java.util.List;
import java.util.Map;

public interface SeatScheduleService extends BaseService<SeatScheduleDto>{
    List<OrderDetailResponse> create(Long seanceId, Map<Long, PriceType> priceTypeMap);
    List<SeatScheduleDto>findByRoomMovieId(Long roomMovieId);
    List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId);


}
