package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.requests.SeatRequest;

import java.util.List;

public interface SeatService extends BaseService<SeatDto>{
    SeatDto create(SeatRequest request);
    List<SeatDto> findByRoomId(Long id);



}
