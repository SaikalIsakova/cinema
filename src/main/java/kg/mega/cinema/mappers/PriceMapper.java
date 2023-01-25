package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PriceMapper extends BaseMapper<Price, PriceDto>{
    PriceMapper INSTANCE= Mappers.getMapper(PriceMapper.class);

}
