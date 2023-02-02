package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.PriceRep;
import kg.mega.cinema.mappers.PriceMapper;
import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.requests.SavePriceRequest;
import kg.mega.cinema.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    PriceMapper mapper = PriceMapper.INSTANCE;

    private final PriceRep rep;

    public PriceServiceImpl(PriceRep rep) {
        this.rep = rep;
    }

    @Override
    public PriceDto save(PriceDto priceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(priceDto)));
    }

    @Override
    public PriceDto create(SavePriceRequest price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        priceDto.setPriceType(price.getPriceType());

        return save(priceDto);
    }

    @Override
    public PriceDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Price not found!")));
    }

    @Override
    public PriceDto delete(Long id) {
        PriceDto priceDto = findById(id);
        priceDto.setActive(false);
        return save(priceDto);
    }

    @Override
    public List<PriceDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }
}
