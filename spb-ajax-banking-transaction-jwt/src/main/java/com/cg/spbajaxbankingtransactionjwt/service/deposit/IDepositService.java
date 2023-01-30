package com.cg.spbajaxbankingtransactionjwt.service.deposit;

import com.cg.spbajaxbankingtransactionjwt.model.Deposit;
import com.cg.spbajaxbankingtransactionjwt.service.IGeneral;

import java.math.BigDecimal;

public interface IDepositService extends IGeneral<Deposit> {
    String deposits(int id, BigDecimal money);
}
