package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.MovieSessionDto;
import kg.mega.cinema.models.entities.MovieSession;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMovieMapper extends BaseMapper<MovieSession, MovieSessionDto>{
    RoomMovieMapper INSTANCE= Mappers.getMapper(RoomMovieMapper.class);
}
