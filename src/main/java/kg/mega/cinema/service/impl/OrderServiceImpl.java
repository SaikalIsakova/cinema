package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.OrderRep;
import kg.mega.cinema.mappers.OrderMapper;
import kg.mega.cinema.models.dto.OrderDto;
import kg.mega.cinema.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderMapper mapper = OrderMapper.INSTANCE;

    private final OrderRep rep;

    public OrderServiceImpl(OrderRep rep) {
        this.rep = rep;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDto)));
    }

    @Override
    public OrderDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Order not found!")));
    }

    @Override
    public OrderDto delete(Long id) {
        OrderDto orderDto = findById(id);
        orderDto.setActive(false);
        return save(orderDto);
    }

    @Override
    public List<OrderDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public OrderDto create() {

        OrderDto orderDto=new OrderDto();
        orderDto.setPrice(0);

        return save(orderDto);
    }
}
