package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.entities.RoomMoviePrice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMoviePriceMapper extends BaseMapper<RoomMoviePrice, RoomMoviePriceDto>{
    RoomMoviePriceMapper INSTANCE = Mappers.getMapper(RoomMoviePriceMapper.class);


}