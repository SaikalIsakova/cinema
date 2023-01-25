package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.OrderDto;
import kg.mega.cinema.models.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto>{
    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);

}
