package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.model.Transfer;
import com.spbproductmanagementjwt.model.dto.TransferDTO;
import com.spbproductmanagementjwt.service.transfer.ITransferService;
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

    @Autowired
    private ITransferService transferService;

    @GetMapping
    private ResponseEntity<List<TransferDTO>> transferHistory() {
        List<Transfer> transferList = (List<Transfer>) transferService.findAll();

        List<TransferDTO> transferDTOList = transferList.stream().map(Transfer::toTransferDTO).collect(Collectors.toList());

        return new ResponseEntity<>(transferDTOList, HttpStatus.OK);
    }
}
