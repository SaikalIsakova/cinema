package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.RoomMoviePriceRep;
import kg.mega.cinema.exceptions.RoomMoviePriceNotFoundException;
import kg.mega.cinema.mappers.RoomMoviePriceMapper;
import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.enums.PriceType;
import kg.mega.cinema.models.requests.RoomMoviePriceRequest;
import kg.mega.cinema.models.responses.CinemaResponse;
import kg.mega.cinema.models.responses.GetSeanceResponse;
import kg.mega.cinema.models.responses.RoomMovieResp;
import kg.mega.cinema.models.responses.RoomResponse;
import kg.mega.cinema.service.MovieService;
import kg.mega.cinema.service.PriceService;
import kg.mega.cinema.service.RoomMoviePriceService;
import kg.mega.cinema.service.RoomMovieService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {


    RoomMoviePriceMapper mapper = RoomMoviePriceMapper.INSTANCE;

    private final RoomMoviePriceRep rep;
    private final PriceService priceService;
    private final RoomMovieService roomMovieService;
    private final MovieService movieService;



    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep, PriceService priceService,
                                     RoomMovieService roomMovieService, MovieService movieService) {

        this.rep = rep;
        this.priceService = priceService;
        this.roomMovieService = roomMovieService;
        this.movieService = movieService;
    }

    @Override
    public RoomMoviePriceDto save(RoomMoviePriceDto roomMoviePriceDto) {

        return mapper.toDto(rep.save(mapper.toEntity(roomMoviePriceDto)));
    }

    @Override
    public RoomMoviePriceDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomMoviePriceNotFoundException("RoomMoviePrice not found!")));
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

        PriceDto priceDto = priceService.findById(roomMoviePriceRequest.getPriceId());

        RoomMovieDto roomMovieDto = roomMovieService.findById(roomMoviePriceRequest.getRoomMovieId());

        RoomMoviePriceDto roomMoviePriceDto = new RoomMoviePriceDto();
        roomMoviePriceDto.setPrice(priceDto);
        roomMoviePriceDto.setRoomMovie(roomMovieDto);

        return save(roomMoviePriceDto);
    }

    @Override
    public List<RoomMoviePriceDto> getPriceByMovieIdAndDate(Long movieId, LocalDate date) {

        return mapper.toDtos(rep.getPriceByMovieIdAndDate(movieId, date));
    }

    @Override
    public GetSeanceResponse getSeanceByDateAndMovieId(Long movieId, LocalDate date) {

        List<RoomMoviePriceDto> roomMoviePriceList = getPriceByMovieIdAndDate(movieId, date);

        List<RoomMovieDto> roomMovieList = roomMovieService.getRoomMovieByMovieAndDate(movieId, date);

        List<RoomResponse> roomResponses = new ArrayList<>();

        for (RoomMovieDto item : roomMovieList) {

            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setRoomId(item.getRoom().getId());
            roomResponse.setName(item.getRoom().getName());

            List<RoomMovieResp> newSeanceList = new ArrayList<>();

            for (RoomMoviePriceDto item1 : roomMoviePriceList) {
                RoomMovieResp roomMovieResp = new RoomMovieResp();
                if (item.getSchedule().getId().equals(item1.getRoomMovie().getSchedule().getId())) {
                    if (item1.getRoomMovie().getId().equals(item.getId())) {
                        if (item1.getRoomMovie().getRoom().getCinema().getId().equals(item.getRoom().getCinema().getId())) {

                            roomMovieResp.setSeanceId(item1.getRoomMovie().getId());
                            roomMovieResp.setPrice(item1.getPrice().getPrice());
                            roomMovieResp.setStartTime(item1.getRoomMovie().getSchedule().getStartTime());
                            roomMovieResp.setPriceType(item1.getPrice().getPriceType());

                            newSeanceList.add(roomMovieResp);

                        }
                    }
                }

            }

            roomResponse.setSeances(newSeanceList);
            roomResponses.add(roomResponse);
        }

        List<CinemaResponse> cinemaResponses = new ArrayList<>();

        for (RoomMovieDto item : roomMovieList) {

            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(item.getRoom().getCinema().getName());

            List<RoomResponse> newRoomResp = new ArrayList<>();

            for (RoomResponse roomResponseItem : roomResponses) {
                if (item.getRoom().getId().equals(roomResponseItem.getRoomId())) {

                    newRoomResp.add(roomResponseItem);

                }
            }
            cinemaResponse.setRooms(newRoomResp);
            cinemaResponses.add(cinemaResponse);
        }


        MovieDto movieDto=movieService.findById(movieId);
        GetSeanceResponse getSeanceResponse = new GetSeanceResponse();

        getSeanceResponse.setCinemas(cinemaResponses);
        getSeanceResponse.setName(movieDto.getName());
        getSeanceResponse.setPg(movieDto.getPg());
        getSeanceResponse.setImage(movieDto.getImage());
        getSeanceResponse.setDescription(movieDto.getDefinition());
        getSeanceResponse.setRating(movieDto.getRating());


        return getSeanceResponse;
    }


    @Override
    public RoomMoviePriceDto findByPriceType(Long roomMovieId, PriceType priceType) {

        return mapper.toDto(rep.findByPriceType(roomMovieId, priceType));
    }

    @Override
    public List<RoomMoviePriceDto> getActive(Long movieId, LocalDate date) {

        return mapper.toDtos(rep.getActive(movieId,date));
    }

}
