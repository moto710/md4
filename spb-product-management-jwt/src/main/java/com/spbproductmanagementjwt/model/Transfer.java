package com.spbproductmanagementjwt.model;

import com.spbproductmanagementjwt.model.dto.TransferDTO;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fees", nullable = false)
    private BigDecimal fee;

    @Column(name = "fees_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal feeAmount;

    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "transfer_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transferAmount;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private Customer recipient;

    @OneToOne(targetEntity = Customer.class)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Customer sender;

    public TransferDTO toTransferDTO() {
        return new TransferDTO()
                .setId(id)
                .setTransactionAmount(transactionAmount)
                .setTransferAmount(transferAmount)
                .setRecipientDTO(recipient.toCustomerDTO())
                .setSenderDTO(sender.toCustomerDTO())
                ;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "recipient=" + recipient.getId() +
                ", sender=" + sender.getId() +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
