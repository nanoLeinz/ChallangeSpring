package id.nano.challangespring.service;

import id.nano.challangespring.entity.Order;
import id.nano.challangespring.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface OrderService {
    public ResponseEntity<Object> insert(UUID userId, Order order);

    public ResponseEntity<Object> delete(UUID id);

    public ResponseEntity<Object> getById(UUID id);
}
