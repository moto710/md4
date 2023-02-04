package com.spbproductmanagementjwt.service.order;


import com.spbproductmanagementjwt.model.Order;
import com.spbproductmanagementjwt.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
