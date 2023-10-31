package id.nano.challangespring.service.impl;

import id.nano.challangespring.entity.Merchant;
import id.nano.challangespring.repository.MerchantRepository;
import id.nano.challangespring.service.MerchantService;
import id.nano.challangespring.utils.ResponseHandler;
import id.nano.challangespring.utils.SimpleStringUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.log4j.Log4j2;
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

    @Override
    public ResponseEntity<Object> insert(Merchant merchant) {
        Merchant data = merchantRepository.save(merchant);

        return ResponseHandler.generateResponse("Inserted Successfully", HttpStatus.OK, data);
    }

    @Override
    public ResponseEntity<Object> update(Merchant merchant) {
        try {
            Merchant data = merchantRepository.getById(merchant.getId());
            data.setOpen(merchant.getOpen());
            data.setMerchantName(merchant.getMerchantName());
            data.setMerchantLocation(merchant.getMerchantLocation());
            return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK, merchantRepository.save(data));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id) {
        try {
            Optional<Merchant> data = merchantRepository.findById(id);
            data.get().setDeletedDate(new Date());
            return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK, merchantRepository.save(data.get()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> getById(UUID id) {
        try {
            Optional<Merchant> data = merchantRepository.findById(id);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data.get());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_GATEWAY, null);
        }
    }

    @Override
    public ResponseEntity<Object> findAll() {
        try {
            List<Merchant> data = merchantRepository.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, data);
        } catch (Exception e) {
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
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, list);
    }
}
