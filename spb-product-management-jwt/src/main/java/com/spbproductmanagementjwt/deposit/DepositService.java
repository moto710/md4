package com.spbproductmanagementjwt.deposit;

import com.spbproductmanagementjwt.deposit.Deposit;
import com.spbproductmanagementjwt.deposit.IDepositRepository;
import com.spbproductmanagementjwt.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepositService implements IDepositService {
    @PersistenceContext
    private EntityManager entityManager;

    private final IDepositRepository depositRepository;

    @Autowired
    public DepositService(IDepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public List<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById(id);
    }

    @Override
    public void save(Deposit deposit) {
        depositRepository.save(deposit);
    }

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }

    @Override
    public String deposits(Long id, BigDecimal money) {
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
