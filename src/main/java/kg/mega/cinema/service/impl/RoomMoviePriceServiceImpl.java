package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomMoviePriceRep;
import kg.mega.cinema.mappers.RoomMoviePriceMapper;
import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.requests.RoomMoviePriceRequest;
import kg.mega.cinema.models.responses.CinemaResponse;
import kg.mega.cinema.models.responses.JsonResponse;
import kg.mega.cinema.models.responses.RoomMovieResp;
import kg.mega.cinema.models.responses.RoomResponse;
import kg.mega.cinema.service.PriceService;
import kg.mega.cinema.service.RoomMoviePriceService;
import kg.mega.cinema.service.RoomMovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {

    RoomMoviePriceMapper mapper= RoomMoviePriceMapper.INSTANCE;

    private final RoomMoviePriceRep rep;
    private final PriceService priceService;
    private final RoomMovieService roomMovieService;


    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep, PriceService priceService, RoomMovieService roomMovieService) {
        this.rep = rep;
        this.priceService = priceService;
        this.roomMovieService = roomMovieService;
    }

    @Override
    public RoomMoviePriceDto save(RoomMoviePriceDto roomMoviePriceDto) {

        return mapper.toDto(rep.save(mapper.toEntity(roomMoviePriceDto)));
    }

    @Override
    public RoomMoviePriceDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException(" сеанс-цена не найдена")));
    }

    @Override
    public RoomMoviePriceDto delete(Long id) {

        RoomMoviePriceDto roomMoviePriceDto = findById(id);
        roomMoviePriceDto.setActive(false);
        return save(roomMoviePriceDto);

    }

    @Override
    public List<RoomMoviePriceDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }

    @Override
    public RoomMoviePriceDto create(RoomMoviePriceRequest roomMoviePriceRequest) {
        PriceDto priceDto=priceService.findById(roomMoviePriceRequest.getPriceId());
        RoomMovieDto roomMovieDto=roomMovieService.findById(roomMoviePriceRequest.getRoomMovieId());

        RoomMoviePriceDto roomMoviePriceDto1=new RoomMoviePriceDto();
        roomMoviePriceDto1.setPrice(priceDto);
        roomMoviePriceDto1.setRoomMovie(roomMovieDto);

        return save(roomMoviePriceDto1);
    }

    @Override
    public List<RoomMoviePriceDto> getPriceByMovieIdAndDate(Long movieId,LocalDate date) {

        return mapper.toDtos(rep.getPriceByMovieIdAndDate(movieId,date));
    }

    @Override
    public JsonResponse getSeanceOutput(Long movieId, LocalDate date) {

        List<RoomMoviePriceDto> roomMoviePriceDtos = getPriceByMovieIdAndDate(movieId, date);

//
//        List<RoomMovieResp> roomMovieResps = new ArrayList<>();
//
//        for (RoomMoviePriceDto item : roomMoviePriceDtos) { //5
//            RoomMovieResp roomMovieResp = new RoomMovieResp();
//            roomMovieResp.setId(item.getRoomMovie().getId());
//            roomMovieResp.setPrice(item.getPrice().getPrice());
//            roomMovieResp.setPriceType(item.getPrice().getPriceType());
//            roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
//
//            roomMovieResps.add(roomMovieResp);
//        }




        List<RoomMovieDto> roomMovieDtos = roomMovieService.getRoomMovieByMovieAndDate(movieId, date);

        List<RoomResponse> roomResponses = new ArrayList<>();

        for (RoomMovieDto item : roomMovieDtos) {

            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setRoomId(item.getRoom().getId());
            roomResponse.setName(item.getRoom().getName());

            List<RoomMovieResp>newList=new ArrayList<>();
            //TODO check seance
            for(RoomMoviePriceDto item1:roomMoviePriceDtos) {
                RoomMovieResp roomMovieResp = new RoomMovieResp();
                if (item.getSchedule().getId().equals(item1.getRoomMovie().getSchedule().getId())) {
                    if (item1.getRoomMovie().getId().equals(item.getId())) {
                        roomMovieResp.setId(item1.getRoomMovie().getSchedule().getId());
                        roomMovieResp.setPrice(item1.getPrice().getPrice());
                        roomMovieResp.setStartTime(item1.getRoomMovie().getSchedule().getStartTime());
                        roomMovieResp.setPriceType(item1.getPrice().getPriceType());
                        newList.add(roomMovieResp);
                    }
                }
            }

            roomResponse.setSeances(newList);
            roomResponses.add(roomResponse);
        }

        List<CinemaResponse> cinemaResponses = new ArrayList<>();

        for (RoomMovieDto item : roomMovieDtos) {

            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(item.getRoom().getCinema().getName());
            List<RoomResponse> newRoomResp = new ArrayList();
            for (RoomResponse roomResponseItem : roomResponses) {

                if (item.getRoom().getId().equals(roomResponseItem.getRoomId())) {
                    newRoomResp.add(roomResponseItem);
                }
            }
            cinemaResponse.setRooms(newRoomResp);
            cinemaResponses.add(cinemaResponse);
        }


        JsonResponse jsonResponse = new JsonResponse();
        for (RoomMoviePriceDto item : roomMoviePriceDtos) {
            jsonResponse.setCinemas(cinemaResponses);
            jsonResponse.setName(item.getRoomMovie().getMovie().getName());
            jsonResponse.setPg(item.getRoomMovie().getMovie().getPg());
            jsonResponse.setImage(item.getRoomMovie().getMovie().getImage());
            jsonResponse.setDescription(item.getRoomMovie().getMovie().getDefinition());
            jsonResponse.setRating(item.getRoomMovie().getMovie().getRating());
        }
        return jsonResponse;
    }

    @Override
    public RoomMoviePriceDto getPriceBySeatSchedule(Long seatScheduleId) {
        return mapper.toDto(rep.getPriceBySeatSchedule(seatScheduleId));
    }

}

