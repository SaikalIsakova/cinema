package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatRep;
import kg.mega.cinema.exceptions.SeatNotFoundException;
import kg.mega.cinema.mappers.SeatMapper;
import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.requests.SeatRequest;
import kg.mega.cinema.models.responses.Response;
import kg.mega.cinema.service.RoomService;
import kg.mega.cinema.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    SeatMapper mapper = SeatMapper.INSTANCE;

    private final SeatRep rep;
    private final RoomService roomService;

    public SeatServiceImpl(SeatRep rep,RoomService roomService) {
        this.rep = rep;
        this.roomService = roomService;
    }

    @Override
    public SeatDto save(SeatDto seatDto) {

        return mapper.toDto(rep.save(mapper.toEntity(seatDto)));
    }

    @Override
    public SeatDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new SeatNotFoundException("Seat nor found!")));
    }

    @Override
    public SeatDto delete(Long id) {

        SeatDto seatDto = findById(id);
        seatDto.setActive(false);

        return save(seatDto);
    }

    @Override
    public List<SeatDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public Response create(SeatRequest request) {

        RoomDto roomDto=roomService.findById(request.getRoomId());


        for (int i = 1; i <=request.getRow(); i++) {
            for (int j=1; j <= request.getNumber()/request.getRow(); j++) {
                SeatDto seatDto=new SeatDto();
                seatDto.setRoom(roomDto);
                seatDto.setRow(i);
                seatDto.setNumber(j);
                save(seatDto);
            }

        }

        return new Response("Места успешно сохранены!");
    }

    @Override
    public List<SeatDto> findByRoomId(Long id) {

        return mapper.toDtos(rep.findByRoomId(id));
    }


}
