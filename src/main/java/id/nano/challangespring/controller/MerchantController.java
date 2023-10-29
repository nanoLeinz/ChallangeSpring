package id.nano.challangespring.controller;


import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@RestController
public class MerchantController {
    @Autowired
    public MerchantService merchantService;
    @PostMapping(value = {"/merchant", "/merchant/"})
    public ResponseEntity<Object> saveMerchant(@RequestBody @Valid Merchant request) {
        return merchantService.insert(request);
    }
    @PutMapping(value = {"/merchant", "/merchant/"})
    public ResponseEntity<Object> updateMerchant(@RequestBody @Valid Merchant request) {
        return merchantService.update(request);
    }
    @DeleteMapping(value = {"/merchant/{id}"})
    public ResponseEntity<Object> deleteMerchant(@PathVariable("id") UUID id) {
        return merchantService.delete(id);
    }
    @GetMapping(value = {"/merchant/{id}"})
    public ResponseEntity<Object> getMerchant(@PathVariable("id") UUID id) {
        return merchantService.getById(id);
    }
    @GetMapping(value = {"/merchant/all"})
    public ResponseEntity<Object> getAllMerchant() {
        return merchantService.findAll();
    }

    @GetMapping(value = {"/merchant", "/merchant/"})
    public ResponseEntity<Object> merchantCriteria(
            @RequestParam() Integer page,
            @RequestParam(required = true) Integer size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        return merchantService.findAllbyCriteria(page,size,orderby,ordertype,status);
    }
}
