package com.bankingtransaction.service.deposit;

import com.bankingtransaction.model.Deposit;
import com.bankingtransaction.service.IGeneral;

import java.math.BigDecimal;

public interface IDepositService extends IGeneral<Deposit> {
    public void deposits(int id, BigDecimal money);
}
