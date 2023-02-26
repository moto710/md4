package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.transfer.Transfer;
import com.spbproductmanagementjwt.transfer.TransferDTO;
import com.spbproductmanagementjwt.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transfers")
public class TransferRestController {

    private final ITransferService transferService;

    @Autowired
    public TransferRestController(ITransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    private ResponseEntity<List<TransferDTO>> transferHistory() {
        List<Transfer> transferList = transferService.findAll();

        List<TransferDTO> transferDTOList = transferList.stream().map(Transfer::toTransferDTO).collect(Collectors.toList());

        return new ResponseEntity<>(transferDTOList, HttpStatus.OK);
    }
}
