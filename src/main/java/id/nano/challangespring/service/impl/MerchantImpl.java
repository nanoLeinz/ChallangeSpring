package id.nano.challangespring.service.impl;

import id.nano.challangespring.ChallangeSpringApplication;
import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.repository.MerchantRepository;
import id.nano.challangespring.service.MerchantService;
import id.nano.challangespring.utils.ResponseHandler;
import id.nano.challangespring.utils.SimpleStringUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MerchantImpl implements MerchantService {

    @Autowired
    public MerchantRepository merchantRepository;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();
    private static final Logger logger = LoggerFactory.getLogger(MerchantImpl.class);

    @Override
    public ResponseEntity<Object> insert(Merchant merchant) {
        Merchant data = merchantRepository.save(merchant);
        logger.info("Data Saved : " + data);
        return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, data);
    }

    @Override
    public ResponseEntity<Object> update(Merchant merchant) {
        try {
            Merchant data = merchantRepository.getById(merchant.getId());
            logger.info("Get Data : " + data);
            data.setOpen(merchant.getOpen());
            data.setMerchantName(merchant.getMerchantName());
            data.setMerchantLocation(merchant.getMerchantLocation());
            logger.info("Updated Succesfully : " + data);
            return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, merchantRepository.save(data));
        } catch (Exception e) {
            logger.warn("Failed to Update : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id) {
        try {
            Optional<Merchant> data = merchantRepository.findById(id);
            data.get().setDeletedDate(new Date());
            logger.info("Deleted Succesfully : " + data);
            return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, merchantRepository.save(data.get()));
        } catch (Exception e) {
            logger.warn("Failed to Delete : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> getById(UUID id) {
        try {
            Optional<Merchant> data = merchantRepository.findById(id);
            logger.info("Get Data : " + data);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data.get());
        } catch (Exception e) {
            logger.warn("Failed to find data : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        try {
            List<Merchant> data = merchantRepository.findAll();
            logger.info("Get Datas : " + data);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        } catch (Exception e) {
            logger.warn("Failed to get datas : " + e.getLocalizedMessage());
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> findAllbyCriteria(Integer page, Integer size, String orderby, String ordertype, String status) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Specification<Merchant> spec =
                ((root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (status != null) {
                        predicates.add(criteriaBuilder.equal(root.get("open"), Boolean.parseBoolean(status)));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                });
        Page<Merchant> list = merchantRepository.findAll(spec, show_data);
        logger.info("Get Datas : " + list);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, list);
    }
}
