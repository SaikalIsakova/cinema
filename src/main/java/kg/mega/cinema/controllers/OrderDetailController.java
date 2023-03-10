package kg.mega.cinema.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.OrderDetailDto;
import kg.mega.cinema.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Детали заявки")
@RestController
@RequestMapping("/api/v1/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody OrderDetailDto orderDetailDto) {
        try {
            return new ResponseEntity<>(service.save(orderDetailDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/find/by/id")
    @ApiOperation("Поиск деталей брони по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }


    @GetMapping("/booking")
    @ApiOperation("Покупка билета")
    ResponseEntity<?> booking(@RequestParam Long id) {

        return new ResponseEntity<>(service.booking(id), HttpStatus.FOUND);

    }


    @GetMapping("/find/all")
    @ApiOperation("Вывод всех деталей брони")
    ResponseEntity<List<OrderDetailDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
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
