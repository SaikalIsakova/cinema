package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.CinemaDto;
import kg.mega.cinema.models.entities.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CinemaMapper extends BaseMapper<Cinema, CinemaDto>{
    CinemaMapper INSTANCE= Mappers.getMapper(CinemaMapper.class);

}
