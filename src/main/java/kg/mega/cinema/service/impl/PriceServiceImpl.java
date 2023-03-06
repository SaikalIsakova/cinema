package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.PriceRep;
import kg.mega.cinema.exceptions.PriceNotFoundException;
import kg.mega.cinema.mappers.PriceMapper;
import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.requests.PriceRequest;
import kg.mega.cinema.models.responses.PriceResponse;
import kg.mega.cinema.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PriceDto create(PriceRequest price) {

        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        priceDto.setPriceType(price.getPriceType());

        return save(priceDto);
    }

    @Override
    public List<PriceResponse> getAllPrices() {

        List<PriceDto>priceList=findAll();

        List<PriceResponse>responseList=new ArrayList<>();

        for(PriceDto item:priceList){

         PriceResponse priceResponse=new PriceResponse();
         priceResponse.setId(item.getId());
         priceResponse.setPrice(item.getPrice());
         priceResponse.setPriceType(item.getPriceType());

         responseList.add(priceResponse);

        }
        return responseList;
    }

    @Override
    public PriceDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new PriceNotFoundException("Price not found!")));
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
