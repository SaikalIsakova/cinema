package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomRep;
import kg.mega.cinema.mappers.RoomMapper;
import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRep rep;

    RoomMapper mapper=RoomMapper.INSTANCE;

    @Override
    public RoomDto save(RoomDto roomDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomDto)));
    }

    @Override
    public RoomDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Room is not found")));
    }

    @Override
    public List<RoomDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public RoomDto delete(Long id) {

        RoomDto roomDto=findById(id);
        roomDto.setActive(false);
        return save(roomDto);
    }
}
