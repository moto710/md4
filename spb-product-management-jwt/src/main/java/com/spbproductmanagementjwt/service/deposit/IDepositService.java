package com.spbproductmanagementjwt.service.deposit;

import com.spbproductmanagementjwt.model.Deposit;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.math.BigDecimal;

public interface IDepositService extends IGeneralService<Deposit> {
    String deposits(Long id, BigDecimal money);
}
