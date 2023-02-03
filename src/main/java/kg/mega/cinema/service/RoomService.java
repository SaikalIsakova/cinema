package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.requests.SaveRoomRequest;

public interface RoomService extends BaseService<RoomDto>{
    RoomDto create(SaveRoomRequest room);
    RoomDto findByRoomMovieId(Long roomMovieId);
}
