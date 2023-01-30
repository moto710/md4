package com.cg.spbajaxbankingtransactionjwt.service.deposit;

import com.cg.spbajaxbankingtransactionjwt.model.Deposit;
import com.cg.spbajaxbankingtransactionjwt.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class DepositService implements IDepositService {
    @PersistenceContext
    private EntityManager entityManager;
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

    @Override
    public String deposits(int id, BigDecimal money) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_deposit");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.setParameter(1,id);
        query.setParameter(2, money);
        query.execute();
        return (String) query.getOutputParameterValue(3);
    }
}
