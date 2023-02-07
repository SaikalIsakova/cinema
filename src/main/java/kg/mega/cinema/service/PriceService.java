package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.requests.PriceRequest;
import kg.mega.cinema.models.responses.PriceResponse;

import java.util.List;

public interface PriceService extends BaseService<PriceDto>{
    PriceDto create(PriceRequest price);
    List<PriceResponse>getAllPrices();
}
