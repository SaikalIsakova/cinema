package kg.mega.cinema.service;



import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.responses.JsonResponse;
import kg.mega.cinema.models.request.RoomMoviePriceRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{
    RoomMoviePriceDto create(RoomMoviePriceRequest roomMoviePriceRequest);
    List<RoomMoviePriceDto> getPriceByRoomMovieId(Long roomMovieId);
    JsonResponse getSeanceOutput(Long movieId, LocalDate date);

}
