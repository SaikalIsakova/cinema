package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.requests.PriceRequest;

public interface PriceService extends BaseService<PriceDto>{
    PriceDto create(PriceRequest price);
}
