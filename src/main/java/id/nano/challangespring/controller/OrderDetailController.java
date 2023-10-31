package id.nano.challangespring.controller;

import id.nano.challangespring.entity.Order;
import id.nano.challangespring.entity.OrderDetail;
import id.nano.challangespring.entity.dto.OrderDetailDto;
import id.nano.challangespring.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderDetailController {
    @Autowired
    public OrderDetailService orderDetailService;

    @PostMapping(value = {"/order/{orderId}/product", "/user/{orderId}/product/"})
    public ResponseEntity<Object> saveOrderDetail(@PathVariable("orderId") UUID orderId, @RequestBody List<OrderDetailDto> orders) {
        return orderDetailService.insert(orderId,orders);
    }

    @GetMapping(value = {"/order/{orderId}/product", "/user/{orderId}/product/"})
    public ResponseEntity<Object> getAllOrderDetail(@PathVariable("orderId") UUID orderId) {
        return orderDetailService.getAllDetailByOrderId(orderId);
    }
}
