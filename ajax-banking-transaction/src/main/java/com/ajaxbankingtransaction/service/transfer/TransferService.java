package com.ajaxbankingtransaction.service.transfer;

import com.ajaxbankingtransaction.model.Transfer;
import com.ajaxbankingtransaction.repository.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService implements ITransferService {
    @Autowired
    private ITransferRepository transferRepository;

    @Override
    public Iterable<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Optional<Transfer> findById(int id) {
        return transferRepository.findById(id);
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public void remove(int id) {
        transferRepository.deleteById(id);
    }

    @Override
    public void remove(Transfer transfer) {
        transferRepository.delete(transfer);
    }
}
