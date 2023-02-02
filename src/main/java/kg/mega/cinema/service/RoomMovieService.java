package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.requests.SaveRoomMovieRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomMovieService extends BaseService<RoomMovieDto>{
    RoomMovieDto create(SaveRoomMovieRequest request);
    List<RoomMovieDto> findByRoomId(Long roomId);
    List<RoomMovieDto>getSeance(Long movieId,LocalDate date);


}
