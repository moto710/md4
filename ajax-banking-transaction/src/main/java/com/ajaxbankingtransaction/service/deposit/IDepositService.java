package com.ajaxbankingtransaction.service.deposit;

import com.ajaxbankingtransaction.model.Deposit;
import com.ajaxbankingtransaction.service.IGeneral;

import java.math.BigDecimal;

public interface IDepositService extends IGeneral<Deposit> {
    String deposits(int id, BigDecimal money);
}
