package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.requests.SeatRequest;
import kg.mega.cinema.models.responses.Response;

import java.util.List;

public interface SeatService extends BaseService<SeatDto>{
    Response create(SeatRequest request);
    List<SeatDto> findByRoomId(Long id);



}
