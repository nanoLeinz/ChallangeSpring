package id.nano.challangespring.service;

import id.nano.challangespring.entity.OrderDetail;
import id.nano.challangespring.entity.dto.OrderDetailDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {
    public ResponseEntity<Object> insert(UUID orderId, List<OrderDetailDto> products);

    public ResponseEntity<Object> getAllDetailByOrderId(UUID id);

}