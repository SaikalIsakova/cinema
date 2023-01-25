package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.entities.SeatSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SeatScheduleMapper extends BaseMapper<SeatSchedule, SeatScheduleDto>{
    SeatScheduleMapper INSTANCE= Mappers.getMapper(SeatScheduleMapper.class);

}
