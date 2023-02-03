package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.requests.SaveSeatRequest;

import java.util.List;

public interface SeatService extends BaseService<SeatDto>{
    SeatDto create(SaveSeatRequest request);
    List<SeatDto> findByRoomId(Long id);



}
