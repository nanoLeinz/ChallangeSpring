package id.nano.challangespring.service.impl;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.Order;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.entity.User;
import id.nano.challangespring.repository.OrderRepository;
import id.nano.challangespring.repository.UserRepository;
import id.nano.challangespring.service.OrderService;
import id.nano.challangespring.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderImpl implements OrderService {
    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderImpl.class);
    @Override
    public ResponseEntity<Object> insert(UUID userId, Order order) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty())
                return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND, null);
            order.setUser(userOptional.get());
            logger.info("Inserted Succesfully : " + order);
            return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, orderRepository.save(order));
        } catch (Exception e) {
            logger.warn("Failed to Insert : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, e);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getById(UUID id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) return ResponseHandler.generateResponse("Order Not Found", HttpStatus.NOT_FOUND, null);
        logger.info("Get Datas : " + order);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, order);
    }
}
