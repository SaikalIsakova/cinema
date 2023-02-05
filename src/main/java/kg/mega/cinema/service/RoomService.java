package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.requests.RoomRequest;

public interface RoomService extends BaseService<RoomDto>{
    RoomDto create(RoomRequest room);
    RoomDto findByRoomMovieId(Long roomMovieId);
}
