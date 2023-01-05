package com.bankingtransaction.service.withdraw;

import com.bankingtransaction.model.Withdraw;
import com.bankingtransaction.repository.IWithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WithdrawService implements IWithdrawService{
    @Autowired
    private IWithdrawRepository withdrawRepository;
    @Override
    public Iterable<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public Optional<Withdraw> findById(int id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public void save(Withdraw withdraw) {
withdrawRepository.save(withdraw);
    }

    @Override
    public void remove(int id) {
withdrawRepository.deleteById(id);
    }

    @Override
    public void remove(Withdraw withdraw) {
        withdrawRepository.delete(withdraw);
    }
}
