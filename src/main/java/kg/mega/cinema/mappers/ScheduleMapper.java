package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.ScheduleDto;
import kg.mega.cinema.models.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule, ScheduleDto>{
    ScheduleMapper INSTANCE= Mappers.getMapper(ScheduleMapper.class);

}
