package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.SeatScheduleRep;
import kg.mega.cinema.exceptions.SeatScheduleNotFoundException;
import kg.mega.cinema.mappers.SeatScheduleMapper;
import kg.mega.cinema.models.dto.*;
import kg.mega.cinema.models.enums.PriceType;
import kg.mega.cinema.models.enums.SeatStatus;
import kg.mega.cinema.models.responses.OrderDetailResponse;
import kg.mega.cinema.models.responses.SeatScheduleResponse;
import kg.mega.cinema.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service

public class SeatScheduleServiceImpl implements SeatScheduleService {

    SeatScheduleMapper mapper = SeatScheduleMapper.INSTANCE;

    private final SeatScheduleRep rep;
    private final SeatService seatService;
    private final RoomMoviePriceService roomMoviePriceService;
    private final RoomService roomService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public SeatScheduleServiceImpl(SeatScheduleRep rep, SeatService seatService,
                                   RoomMoviePriceService roomMoviePriceService, RoomService roomService,
                                   OrderService orderDetailService, OrderDetailService orderDetailService1) {
        this.rep = rep;
        this.seatService = seatService;
        this.roomMoviePriceService = roomMoviePriceService;
        this.roomService = roomService;
        this.orderService = orderDetailService;
        this.orderDetailService = orderDetailService1;
    }

    @Override
    public SeatScheduleDto save(SeatScheduleDto seatScheduleDto) {

        return mapper.toDto(rep.save(mapper.toEntity(seatScheduleDto)));
    }

    @Override
    public SeatScheduleDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(() -> new SeatScheduleNotFoundException("SeatSchedule not found!")));
    }

    @Override
    public SeatScheduleDto delete(Long id) {

        SeatScheduleDto seatScheduleDto = findById(id);
        seatScheduleDto.setActive(false);

        return save(seatScheduleDto);
    }

    @Override
    public List<SeatScheduleDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }


    @Override
    public List<OrderDetailResponse> create(Long seanceId, Map<Long, PriceType> priceTypeMap) {

        List<Long> seatScheduleId=new ArrayList<>();

        for (Map.Entry<Long, PriceType> entry : priceTypeMap.entrySet())  {

            SeatScheduleDto seatScheduleDto = new SeatScheduleDto();

            SeatDto seatDto = seatService.findById(entry.getKey());

            RoomMoviePriceDto roomMoviePriceDto=roomMoviePriceService.findByPriceType(seanceId,entry.getValue());

            if (entry.getValue()==PriceType.CHILD){

                seatScheduleDto.setRoomMoviePrice(roomMoviePriceDto);
            } else{

                seatScheduleDto.setRoomMoviePrice(roomMoviePriceDto);
            }


            seatScheduleDto.setSeat(seatDto);
            seatScheduleDto.setSeatStatus(SeatStatus.SOLD);

            seatScheduleDto=save(seatScheduleDto);

            seatScheduleId.add(seatScheduleDto.getId());
        }



        OrderDto orderDto=orderService.create();

        List<OrderDetailDto>orderDetailList=new ArrayList<>();

        List<OrderDetailResponse>responseList=new ArrayList<>();

        for(Long item:seatScheduleId){

            SeatScheduleDto seatScheduleDto=findById(item);

            OrderDetailDto orderDetailDto=new OrderDetailDto();
            orderDetailDto.setSeatSchedule(seatScheduleDto);
            orderDetailDto.setOrder(orderDto);

            orderDetailService.save(orderDetailDto);
            orderDetailList.add(orderDetailDto);
        }

        for(OrderDetailDto item:orderDetailList){

            OrderDetailResponse response=new OrderDetailResponse();

            response.setOrderNumber(item.getOrder().getId());
            response.setCinema(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getRoom().getCinema().getName());
            response.setRoom(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getRoom().getName());
            response.setMovie(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getMovie().getName());
            response.setStarTime(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getSchedule().getStartTime());
            response.setRow(item.getSeatSchedule().getSeat().getRow());
            response.setNumber(item.getSeatSchedule().getSeat().getNumber());
            response.setCost(item.getSeatSchedule().getRoomMoviePrice().getPrice().getPrice());
            responseList.add(response);

        }

        return responseList;


    }

    @Override
    public List<SeatScheduleDto> findByRoomMovieId(Long roomMovieId) {

        return mapper.toDtos(rep.findByRoomMovieId(roomMovieId));
    }

    @Override
    public List<SeatScheduleResponse> getByRoomMovieId(Long roomMovieId) {

        List<SeatScheduleDto> seatScheduleList = findByRoomMovieId(roomMovieId);

        RoomDto roomDto = roomService.findByRoomMovieId(roomMovieId);

        List<SeatDto> seatList = seatService.findByRoomId(roomDto.getId());

        List<SeatScheduleResponse> seatScheduleResList = new ArrayList<>();


        for (SeatDto seatItem:seatList){

            SeatScheduleResponse response=new SeatScheduleResponse();

            if (seatScheduleList.isEmpty()) {
                response.setId(seatItem.getId());
                response.setRow(seatItem.getRow());
                response.setSeatNum(seatItem.getNumber());
                response.setStatus(SeatStatus.FREE);
            }

            for(SeatScheduleDto seatScheduleItem:seatScheduleList){

                SeatScheduleDto seatScheduleDto= seatScheduleItem;

                if(seatItem.getId().equals(seatScheduleDto.getSeat().getId())){

                    response.setId(seatScheduleDto.getSeat().getId());
                    response.setRow(seatScheduleDto.getSeat().getRow());
                    response.setSeatNum(seatScheduleDto.getSeat().getNumber());
                    response.setStatus(seatScheduleDto.getSeatStatus());
                    break;

                } else{

                    response.setId(seatItem.getId());
                    response.setRow(seatItem.getRow());
                    response.setSeatNum(seatItem.getNumber());
                    response.setStatus(SeatStatus.FREE);
                }
            }
            seatScheduleResList.add(response);
        }
        return seatScheduleResList;
    }


}


