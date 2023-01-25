package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.entities.RoomMovie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoomMovieMapper extends BaseMapper<RoomMovie, RoomMovieDto>{
    RoomMovieMapper INSTANCE= Mappers.getMapper(RoomMovieMapper.class);

}
