package com.spbproductmanagementjwt.transfer;

import com.spbproductmanagementjwt.customer.CustomerDTO;
import com.spbproductmanagementjwt.transfer.Transfer;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TransferDTO {

    private Long id;

    private BigDecimal transactionAmount;

    private BigDecimal transferAmount;

    private CustomerDTO recipientDTO;

    private CustomerDTO senderDTO;

    public Transfer toTransfer() {
        return new Transfer()
                .setId(id)
                .setTransactionAmount(transactionAmount)
                .setTransferAmount(transferAmount)
                .setRecipient(recipientDTO.toCustomer())
                .setSender(senderDTO.toCustomer())
                ;
    }
}
