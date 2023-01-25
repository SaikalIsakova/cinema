package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.OrderDetailRep;
import kg.mega.cinema.mappers.OrderDetailMapper;
import kg.mega.cinema.models.dto.OrderDetailDto;
import kg.mega.cinema.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRep rep;

    OrderDetailMapper mapper=OrderDetailMapper.INSTANCE;

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDetailDto)));
    }

    @Override
    public OrderDetailDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Заявка не найдена")));
    }

    @Override
    public List<OrderDetailDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public OrderDetailDto delete(Long id) {

        OrderDetailDto orderDetailDto=findById(id);
        orderDetailDto.setActive(false);
        return save(orderDetailDto);
    }
}
