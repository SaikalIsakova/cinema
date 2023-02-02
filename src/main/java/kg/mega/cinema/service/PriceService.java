package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.requests.SavePriceRequest;

public interface PriceService extends BaseService<PriceDto>{
    PriceDto create(SavePriceRequest price);
}
