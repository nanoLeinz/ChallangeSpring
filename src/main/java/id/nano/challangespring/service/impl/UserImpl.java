package id.nano.challangespring.service.impl;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.entity.User;
import id.nano.challangespring.repository.UserRepository;
import id.nano.challangespring.service.UserService;
import id.nano.challangespring.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Autowired
    public UserRepository userRepository;
    @Override
    public ResponseEntity<Object> insert(User user) {
        User data = userRepository.save(user);
        return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, data);
    }

    @Override
    public ResponseEntity<Object> update(User user) {
        try {
            User data = userRepository.getById(user.getId());
            data.setUsername(user.getUsername());
            data.setEmailAddress(user.getEmailAddress());
            data.setPassword(user.getPassword());
            return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, userRepository.save(data));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id) {
        try {
            Optional<User> data = userRepository.findById(id);
            if (data.isEmpty())
                return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND, null);
            data.get().setDeletedDate(new Date());
            return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, userRepository.save(data.get()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> getById(UUID id) {
        try {
            Optional<User> data = userRepository.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data.get());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        return null;
    }
}
