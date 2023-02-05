package kg.mega.cinema.service;

import kg.mega.cinema.models.dto.OrderDetailDto;
import kg.mega.cinema.models.entities.OrderDetail;
import kg.mega.cinema.models.responses.OrderDetailResponse;
import kg.mega.cinema.models.responses.OrderResponse;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{

   List <OrderDetailResponse> create(List<Long>seatScheduleId);

   List<OrderDetailDto>findByOrderId(Long orderId);

   OrderResponse booking(Long orderId);

}
