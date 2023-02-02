package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeatScheduleMapper extends BaseMapper<kg.mega.cinema.models.entities.Ticket, TicketDto>{
    SeatScheduleMapper INSTANCE= Mappers.getMapper(SeatScheduleMapper.class);
}
