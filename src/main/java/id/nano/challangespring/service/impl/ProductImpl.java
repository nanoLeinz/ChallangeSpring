package id.nano.challangespring.service.impl;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.repository.MerchantRepository;
import id.nano.challangespring.repository.ProductRepository;
import id.nano.challangespring.service.MerchantService;
import id.nano.challangespring.service.ProductService;
import id.nano.challangespring.utils.ResponseHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public MerchantRepository merchantRepository;

    @Override
    public ResponseEntity<Object> insert(UUID merchantId, Product product) {
        try {
            Optional<Merchant> merchant = merchantRepository.findById(merchantId);
            if (merchant.isEmpty())
                return ResponseHandler.generateResponse("Merchant Not Found", HttpStatus.NOT_FOUND, null);
            product.setMerchant(merchant.get());
            return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, productRepository.save(product));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, e);
        }
    }

    @Override
    public ResponseEntity<Object> getById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        System.out.println(product.get().getMerchant());
        if (product.isEmpty()) return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, null);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, product);
    }

    @Override
    public ResponseEntity<Object> findAllProductByMerchantId(UUID id) {
        Optional<List<Product>> productList = Optional.ofNullable(productRepository.findByMerchant_Id(id));
        if (productList.get().isEmpty())
            return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, null);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, productList.get());
    }

    @Override
    public ResponseEntity<Object> update(Product product) {
        try {
            Optional<Product> data = productRepository.findById(product.getId());
            if (data.isEmpty())
                return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, null);
            data.get().setProductName(product.getProductName());
            data.get().setPrice(product.getPrice());
            return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, productRepository.save(data.get()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id) {
        try {
            Optional<Product> data = productRepository.findById(id);
            if (data.isEmpty())
                return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, null);
            data.get().setDeletedDate(new Date());
            return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, productRepository.save(data.get()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }
    @Override
    public ResponseEntity<Object> findAll() {
        return null;
    }
}
