package id.nano.challangespring.controller;


import id.nano.challangespring.entity.Order;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    public OrderService orderService;
    @PostMapping(value = {"/user/{userId}/order", "/user/{userId}/order/"})
    public ResponseEntity<Object> saveOrder(@PathVariable("userId") UUID userId, @RequestBody Order request) {
        return orderService.insert(userId,request);
    }

    @GetMapping(value = {"/order/{id}"})
    public ResponseEntity<Object> getOrder(@PathVariable("id") UUID id) {
        return orderService.getById(id);
    }

}
