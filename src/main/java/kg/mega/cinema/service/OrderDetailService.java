package kg.mega.cinema.service;

import kg.mega.cinema.models.dto.OrderDetailDto;
import kg.mega.cinema.models.responses.OrderResponse;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{


   List<OrderDetailDto>findByOrderId(Long orderId);

   OrderResponse booking(Long orderId);

}
