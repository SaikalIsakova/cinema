package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.requests.SaveSeatRequest;

public interface SeatService extends BaseService<SeatDto>{
    SeatDto create(SaveSeatRequest request);



}
