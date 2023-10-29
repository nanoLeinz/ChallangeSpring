package id.nano.challangespring.service;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    public ResponseEntity<Object> insert(UUID merchantId, Product product);

    public ResponseEntity<Object> update(Product product);

    public ResponseEntity<Object> delete(UUID id);

    public ResponseEntity<Object> getById(UUID id);

    public ResponseEntity<Object> findAllProductByMerchantId(UUID id);

    public ResponseEntity<Object> findAll();
}
