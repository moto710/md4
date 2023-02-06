package com.spbproductmanagementjwt.service.withdraw;

import com.spbproductmanagementjwt.model.Withdraw;
import com.spbproductmanagementjwt.repository.IWithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WithdrawService implements IWithdrawService{
    @Autowired
    private IWithdrawRepository withdrawRepository;
    @Override
    public List<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public Optional<Withdraw> findById(Long id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public void save(Withdraw withdraw) {
withdrawRepository.save(withdraw);
    }

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }
}
