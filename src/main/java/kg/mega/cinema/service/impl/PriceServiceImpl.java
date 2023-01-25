package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.PriceRep;
import kg.mega.cinema.mappers.PriceMapper;
import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRep rep;

    PriceMapper mapper=PriceMapper.INSTANCE;

    @Override
    public PriceDto save(PriceDto priceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(priceDto)));
    }

    @Override
    public PriceDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Цена не найдена")));
    }

    @Override
    public List<PriceDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public PriceDto delete(Long id) {

        PriceDto priceDto=findById(id);
        priceDto.setActive(false);
        return save(priceDto);
    }
}