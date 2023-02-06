package com.spbproductmanagementjwt.service.transfer;

import com.spbproductmanagementjwt.model.Transfer;
import com.spbproductmanagementjwt.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService implements ITransferService {
    @Autowired
    private ITransferRepository transferRepository;

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return transferRepository.findById(id);
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }
}
