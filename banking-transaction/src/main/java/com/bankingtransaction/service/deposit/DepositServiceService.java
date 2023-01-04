package com.bankingtransaction.service.deposit;

import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DepositServiceService implements IDepositService {
    @Autowired
    private IDepositRepository depositRepository;
    @Override
    public Iterable<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Optional<Deposit> findById(int id) {
        return depositRepository.findById(id);
    }

    @Override
    public void save(Deposit deposit) {
        depositRepository.save(deposit);
    }

    @Override
    public void remove(int id) {
        depositRepository.deleteById(id);
    }

    @Override
    public void remove(Deposit deposit) {
        depositRepository.delete(deposit);
    }
}
