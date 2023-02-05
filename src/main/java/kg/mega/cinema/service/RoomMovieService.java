package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.requests.RoomMovieRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomMovieService extends BaseService<RoomMovieDto>{
    RoomMovieDto create(RoomMovieRequest request);
    List<RoomMovieDto> findByRoomId(Long roomId);
    List<RoomMovieDto> getRoomMovieByMovieAndDate(Long movieId, LocalDate date);


}
