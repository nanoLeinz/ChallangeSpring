package id.nano.challangespring.service;

import id.nano.challangespring.entity.OrderDetail;

import java.util.Map;

public interface OrderDetailService {
    public Map insert(OrderDetail orderDetail);

    public Map update(OrderDetail orderDetail);

    public Map delete(Long id);

    public Map getById(Long id);
}
