package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.entities.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeatMapper extends BaseMapper<Seat, SeatDto>{
    SeatMapper INSTANCE= Mappers.getMapper(SeatMapper.class);

}
