package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.ScheduleDto;
import kg.mega.cinema.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Api(tags = "Расписание")
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;


    @PostMapping("/save")
    @ApiOperation("Создание")
    ResponseEntity<?> create( @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
                              @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time) {

        return new ResponseEntity<>(service.create(date, time), HttpStatus.CREATED);

    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск расписания по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод всех расписаний")
    ResponseEntity<?> findAllSchedules() {


        return ResponseEntity.ok(service.findAllSchedules());
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
