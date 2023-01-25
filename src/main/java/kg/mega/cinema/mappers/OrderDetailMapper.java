package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.OrderDetailDto;
import kg.mega.cinema.models.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail, OrderDetailDto>{
    OrderDetailMapper INSTANCE= Mappers.getMapper(OrderDetailMapper.class);

}
