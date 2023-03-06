package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.SeatScheduleDto;
import kg.mega.cinema.models.enums.PriceType;
import kg.mega.cinema.models.responses.SeatScheduleResponse;
import kg.mega.cinema.service.SeatScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "Расписание мест")
@RestController
@RequestMapping("/api/v1/seatSchedule")
public class SeatScheduleController {

    @Autowired
    private SeatScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody SeatScheduleDto seatScheduleDto) {
        try {
            return new ResponseEntity<>(service.save(seatScheduleDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }


    @PostMapping("/create")
    @ApiOperation("Сохранение билета")
    ResponseEntity<?> create(@RequestParam Long seanceId,@RequestBody Map<Long, PriceType> seatId) {
        try {
            return new ResponseEntity<>(service.create(seanceId,seatId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }


    @GetMapping("/find/by/id")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }


    @GetMapping("/find/all")
    @ApiOperation("Вывод всех купленных мест")
    ResponseEntity<List<SeatScheduleDto>> findAll() {
        return ResponseEntity.ok(service.findAll());

    }


    @GetMapping("/get/by/room/movie/id")
    @ApiOperation("Вывод всех мест по id сеанса")
    ResponseEntity<List<SeatScheduleResponse>> getByRoomMovieId(@RequestParam Long roomMovieId) {
        return  ResponseEntity.ok(service.getByRoomMovieId(roomMovieId));
    }



    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
