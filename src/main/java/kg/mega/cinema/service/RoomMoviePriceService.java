package kg.mega.cinema.service;



import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.entities.RoomMoviePrice;
import kg.mega.cinema.models.responses.JsonResponse;
import kg.mega.cinema.models.requests.RoomMoviePriceRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{
    RoomMoviePriceDto create(RoomMoviePriceRequest roomMoviePriceRequest);
    List<RoomMoviePriceDto> getPriceByMovieIdAndDate(Long movieId,LocalDate date);
    JsonResponse getSeanceOutput(Long movieId, LocalDate date);
    RoomMoviePriceDto getPriceBySeatSchedule(Long seatScheduleId);

}
