package id.nano.challangespring.service;

import id.nano.challangespring.entity.Merchant;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface MerchantService {
    public ResponseEntity<Object> insert(Merchant merchant);

    public ResponseEntity<Object> update(Merchant merchant);

    public ResponseEntity<Object> delete(UUID id);

    public ResponseEntity<Object> getById(UUID id);

    public ResponseEntity<Object> findAll();

    public ResponseEntity<Object> findAllbyCriteria(Integer page, Integer size, String orderby, String ordertype, String status);
}
