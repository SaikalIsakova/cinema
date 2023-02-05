package kg.mega.cinema.service;

import kg.mega.cinema.models.dto.OrderDto;
import kg.mega.cinema.models.responses.OrderResponse;

import java.util.List;

public interface OrderService extends BaseService<OrderDto>{

    OrderDto create();


}
