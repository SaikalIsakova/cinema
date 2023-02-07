package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.enums.PriceType;
import kg.mega.cinema.models.requests.RoomMoviePriceRequest;
import kg.mega.cinema.models.responses.JsonResponse;

import java.time.LocalDate;
import java.util.List;

public interface RoomMoviePriceService extends BaseService<RoomMoviePriceDto>{
    RoomMoviePriceDto create(RoomMoviePriceRequest roomMoviePriceRequest);
    List<RoomMoviePriceDto> getPriceByMovieIdAndDate(Long movieId,LocalDate date);
    JsonResponse getSeanceOutput(Long movieId, LocalDate date);
    RoomMoviePriceDto findByPriceType(Long roomMovieId, PriceType priceType);



}
