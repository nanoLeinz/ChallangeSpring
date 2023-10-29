package id.nano.challangespring.controller;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.service.MerchantService;
import id.nano.challangespring.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    public ProductService productService;
    @PostMapping(value = {"/merchant/{merchantId}/product", "/merchant/{merchantId}/product/"})
    public ResponseEntity<Object> saveProduct(@PathVariable("merchantId")UUID merchantId, @RequestBody Product request) {
        return productService.insert(merchantId,request);
    }

    @GetMapping(value = {"/merchant/{merchantId}/product", "/merchant/{merchantId}/product/"})
    public ResponseEntity<Object> getProductByMerchantId(@PathVariable("merchantId")UUID merchantId) {
        return productService.findAllProductByMerchantId(merchantId);
    }
    @GetMapping(value = {"/product/{id}"})
    public ResponseEntity<Object> getProduct(@PathVariable("id") UUID id) {
        return productService.getById(id);
    }

    @PutMapping(value = {"/product", "/product/"})
    public ResponseEntity<Object> editProduct(@RequestBody Product product) {
        return productService.update(product);
    }
    @DeleteMapping(value = {"product/{id}"})
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id){
        return productService.delete(id);
    }
}
