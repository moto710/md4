package com.spbproductmanagementjwt.service.orderdetail;

import com.spbproductmanagementjwt.model.OrderDetail;
import com.spbproductmanagementjwt.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
