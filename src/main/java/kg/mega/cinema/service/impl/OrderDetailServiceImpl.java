package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.OrderDetailRep;
import kg.mega.cinema.mappers.OrderDetailMapper;
import kg.mega.cinema.models.dto.*;
import kg.mega.cinema.models.responses.OrderDetailResponse;
import kg.mega.cinema.models.responses.OrderResponse;
import kg.mega.cinema.models.responses.SeatResponse;
import kg.mega.cinema.service.OrderDetailService;
import kg.mega.cinema.service.OrderService;
import kg.mega.cinema.service.RoomMoviePriceService;
import kg.mega.cinema.service.SeatScheduleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailMapper mapper = OrderDetailMapper.INSTANCE;

    private final OrderDetailRep rep;
    private final OrderService orderService;
    private final SeatScheduleService seatScheduleService;
    private final RoomMoviePriceService roomMoviePriceService;


    public OrderDetailServiceImpl(OrderDetailRep rep,OrderService orderService,
                                  SeatScheduleService seatScheduleService,RoomMoviePriceService roomMoviePriceService) {

        this.rep = rep;
        this.orderService=orderService;
        this.seatScheduleService=seatScheduleService;
        this.roomMoviePriceService=roomMoviePriceService;
    }

    @Override
    public OrderDetailDto save(OrderDetailDto orderDetailDto) {
        return mapper.toDto(rep.save(mapper.toEntity(orderDetailDto)));
    }

    @Override
    public OrderDetailDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new RuntimeException("Order detail not found!")));
    }

    @Override
    public OrderDetailDto delete(Long id) {
        OrderDetailDto orderDetailDto = findById(id);
        orderDetailDto.setActive(false);
        return save(orderDetailDto);
    }


    @Override
    public List<OrderDetailDto> findByOrderId(Long orderId) {
        return mapper.toDtos(rep.findByOrderId(orderId));
    }



    @Override
    public List<OrderDetailDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<OrderDetailResponse> create(List<Long> seatScheduleId) {

        OrderDto orderDto=orderService.create();

        List<OrderDetailDto>list=new ArrayList<>();

        List<OrderDetailResponse>responseList=new ArrayList<>();

        for(Long item:seatScheduleId){

            SeatScheduleDto seatScheduleDto=seatScheduleService.findById(item);
            OrderDetailDto orderDetailDto=new OrderDetailDto();
            orderDetailDto.setSeatSchedule(seatScheduleDto);
            orderDetailDto.setOrder(orderDto);
            save(orderDetailDto);
            list.add(orderDetailDto);
        }

            for(OrderDetailDto item:list){
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
    public  OrderResponse  booking(Long orderId) {

        List<OrderDetailDto>orderDetailList=findByOrderId(orderId);
        Set<SeatResponse> seats=new HashSet<>();
        int sum=0;

            OrderResponse orderResponse=new OrderResponse();
        for(OrderDetailDto item:orderDetailList){
            orderResponse.setCinema(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getRoom().getCinema().getName());
            orderResponse.setMovie(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getMovie().getName());
            orderResponse.setRoom(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getRoom().getName());
            orderResponse.setStartTime(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getSchedule().getStartTime());
            orderResponse.setDate(item.getSeatSchedule().getRoomMoviePrice().getRoomMovie().getSchedule().getDateOfFilms());

            for (OrderDetailDto item2:orderDetailList) {
                if (item.getSeatSchedule().getSeat().getId().equals(item2.getSeatSchedule().getSeat().getId())) {
                    SeatResponse seatResponse = new SeatResponse();
                    seatResponse.setRow(item2.getSeatSchedule().getSeat().getRow());
                    seatResponse.setSeatNumber(item2.getSeatSchedule().getSeat().getNumber());
                    seats.add(seatResponse);
                    break;
                }
            }
            orderResponse.setSeats(seats);
                sum += item.getSeatSchedule().getRoomMoviePrice().getPrice().getPrice();
            orderResponse.setSum(sum);
        }

        OrderDto orderDto=orderService.findById(orderId);
        orderDto.setPrice(sum);
        orderDto.setStartTime(orderResponse.getStartTime());
        orderService.save(orderDto);


        return orderResponse;

    }
}
