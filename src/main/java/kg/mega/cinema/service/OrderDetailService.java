package kg.mega.cinema.service;

import kg.mega.cinema.models.dto.OrderDetailDto;
import kg.mega.cinema.models.responses.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{

   List <OrderDetailResponse> create(List<Long>seatScheduleId);
}
