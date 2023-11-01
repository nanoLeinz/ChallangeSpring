package id.nano.challangespring.service.impl;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.entity.Product;
import id.nano.challangespring.entity.User;
import id.nano.challangespring.repository.UserRepository;
import id.nano.challangespring.service.UserService;
import id.nano.challangespring.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);
    @Override
    public ResponseEntity<Object> insert(User user) {
        User data = userRepository.save(user);
        logger.info("Inserted Data : " + data);
        return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, data);
    }

    @Override
    public ResponseEntity<Object> update(User user) {
        try {
            User data = userRepository.getById(user.getId());
            data.setUsername(user.getUsername());
            data.setEmailAddress(user.getEmailAddress());
            data.setPassword(user.getPassword());
            logger.info("Updated Data : " + data);
            return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, userRepository.save(data));
        } catch (Exception e) {
            logger.warn("Failed to update data : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id) {
        try {
            Optional<User> data = userRepository.findById(id);
            if (data.isEmpty())
                return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND, null);
            data.get().setDeletedDate(new Date());
            logger.info("deleted Datas : " + data.get());
            return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, userRepository.save(data.get()));
        } catch (Exception e) {
            logger.warn("Failed to delete data : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> getById(UUID id) {
        try {
            Optional<User> data = userRepository.findById(id);
            logger.info("Get Datas : " + data);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data.get());
        } catch (Exception e) {
            logger.warn("Failed to get data : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        return null;
    }
}
