package id.nano.challangespring.service;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface UserService {
    public ResponseEntity<Object> insert(User user);

    public ResponseEntity<Object> update(User user);

    public ResponseEntity<Object> delete(UUID id);

    public ResponseEntity<Object> getById(UUID id);

    public ResponseEntity<Object> findAll();
}
