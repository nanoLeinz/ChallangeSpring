package id.nano.challangespring.service;

import id.nano.challangespring.entity.Order;

import java.util.Map;

public interface OrderService {
    public Map insert(Order order);

    public Map update(Order Order);

    public Map delete(Long id);

    public Map getById(Long id);
}
