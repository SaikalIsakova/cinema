package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDto>{
    RoomMapper INSTANCE= Mappers.getMapper(RoomMapper.class);

}
