package id.nano.challangespring.service.impl;

import id.nano.challangespring.entity.Order;
import id.nano.challangespring.entity.OrderDetail;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.entity.dto.OrderDetailDto;
import id.nano.challangespring.entity.dto.OrderDetailProductsDto;
import id.nano.challangespring.repository.OrderDetailRepository;
import id.nano.challangespring.repository.OrderRepository;
import id.nano.challangespring.repository.ProductRepository;
import id.nano.challangespring.service.OrderDetailService;
import id.nano.challangespring.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDetailImpl implements OrderDetailService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseEntity<Object> insert(UUID orderId, List<OrderDetailDto> products) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isEmpty())
                return ResponseHandler.generateResponse("Order Not Found", HttpStatus.NOT_FOUND, null);
            if (products.isEmpty())
                return ResponseHandler.generateResponse("Product empty", HttpStatus.BAD_REQUEST, null);
            List<Product> insertedProduct = new ArrayList<>();
            products.forEach(
                    orderDetailDto -> {
                        Optional<Product> productOptional = productRepository.findById(orderDetailDto.getProductId());
                        productOptional.ifPresent(product -> {
                            insertedProduct.add(product);
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setQuantity(orderDetailDto.getQuantity());
                            orderDetail.setOrder(orderOptional.get());
                            orderDetail.setProduct(product);
                            orderDetailRepository.save(orderDetail);
                        });
                    }
            );
            return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, insertedProduct);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getAllDetailByOrderId(UUID id) {
        try {
            List<OrderDetailProductsDto> listOrderDetail = orderDetailRepository.getAllProductOnOrder(id);
//            List<OrderDetail> listOrderDetail = orderDetailRepository.findByOrder_Id(id);
            if (listOrderDetail.isEmpty())
                return ResponseHandler.generateResponse("Data Not Found", HttpStatus.NOT_FOUND, null);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, listOrderDetail);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, e);
        }
    }
}
