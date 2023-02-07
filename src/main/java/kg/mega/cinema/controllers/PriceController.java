package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.PriceDto;
import kg.mega.cinema.models.requests.PriceRequest;
import kg.mega.cinema.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Цена")
@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    @Autowired
    private PriceService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> create(@ModelAttribute PriceRequest price) {
        try {
            return new ResponseEntity<>(service.create(price), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/find/by/id")
    @ApiOperation("Поиск цены по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }

//
//    @GetMapping("/find/all")
//    @ApiOperation("Вывод всех цен")
//    ResponseEntity<?> findAll() {
//        return ResponseEntity.ok(service.findAll());
//    }


    @GetMapping("/get/all/prices")
    @ApiOperation("вывод всех мест")
    ResponseEntity<?> getAllPrices() {
        return ResponseEntity.ok(service.getAllPrices());
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
