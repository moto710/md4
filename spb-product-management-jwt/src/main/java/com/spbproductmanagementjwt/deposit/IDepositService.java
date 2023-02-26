package com.spbproductmanagementjwt.deposit;

import com.spbproductmanagementjwt.deposit.Deposit;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.math.BigDecimal;

public interface IDepositService extends IGeneralService<Deposit> {
    String deposits(Long id, BigDecimal money);
}
