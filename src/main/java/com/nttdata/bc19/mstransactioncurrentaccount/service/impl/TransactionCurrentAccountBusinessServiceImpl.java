package com.nttdata.bc19.mstransactioncurrentaccount.service.impl;

import com.nttdata.bc19.mstransactioncurrentaccount.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountBusiness;
import com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC.CurrentAccountBusiness;
import com.nttdata.bc19.mstransactioncurrentaccount.repository.ITransactionCurrentAccountBusinessRepository;
import com.nttdata.bc19.mstransactioncurrentaccount.request.TransactionCurrentAccountBusinessRequest;
import com.nttdata.bc19.mstransactioncurrentaccount.service.ITransactionCurrentAccountBusinessService;
import com.nttdata.bc19.mstransactioncurrentaccount.util.TransactionTypePasPro;
import com.nttdata.bc19.mstransactioncurrentaccount.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCurrentAccountBusinessServiceImpl implements ITransactionCurrentAccountBusinessService {

    @Autowired
    ITransactionCurrentAccountBusinessRepository transactionCurrentAccountBusinessRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionCurrentAccountBusiness> create(TransactionCurrentAccountBusinessRequest transactionCurrentAccountBusinessRequest) {
        return clientServiceWC.findCurrentAccountBusinessById(transactionCurrentAccountBusinessRequest.getIdCurrentAccountBusiness())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(currentAccountResponse -> this.businessLogicTransaction(transactionCurrentAccountBusinessRequest, currentAccountResponse));
    }

    @Override
    public Mono<TransactionCurrentAccountBusiness> update(TransactionCurrentAccountBusiness transactionCurrentAccountBusiness) {
        transactionCurrentAccountBusiness.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findCurrentAccountBusinessById(transactionCurrentAccountBusiness.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CurrentAccountBusiness -> this.updateTransaction(transactionCurrentAccountBusiness));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionCurrentAccountBusinessRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionCurrentAccountBusiness> findById(String id) {
        return transactionCurrentAccountBusinessRepository.findById(id);
    }

    @Override
    public Flux<TransactionCurrentAccountBusiness> findAll() {
        return transactionCurrentAccountBusinessRepository.findAll();
    }

    @Override
    public Flux<TransactionCurrentAccountBusiness> findByIdCurrentAccountBusiness(String idCurrentAccountBusiness) {
        return transactionCurrentAccountBusinessRepository.findByIdCurrentAccountBusiness(idCurrentAccountBusiness);
    }

    private Mono<TransactionCurrentAccountBusiness> businessLogicTransaction(TransactionCurrentAccountBusinessRequest transactionCurrentAccountBusinessRequest, CurrentAccountBusiness currentAccountBusiness){

        TransactionCurrentAccountBusiness transactionCurrentAccountBusiness = new TransactionCurrentAccountBusiness();
        transactionCurrentAccountBusiness.setId(new ObjectId().toString());
        transactionCurrentAccountBusiness.setIdCurrentAccountBusiness(transactionCurrentAccountBusinessRequest.getIdCurrentAccountBusiness());
        transactionCurrentAccountBusiness.setTransactionTypeCurrentAccount(transactionCurrentAccountBusinessRequest.getTransactionTypeCurrentAccount());
        transactionCurrentAccountBusiness.setTransactionDate(LocalDateTime.now());
        transactionCurrentAccountBusiness.setCreatedAt(LocalDateTime.now());

        if(currentAccountBusiness.getLastTrasactionDate().getMonth().getValue() < LocalDateTime.now().getMonth().getValue()){
            currentAccountBusiness.setNumberMovements(0);
        }
        currentAccountBusiness.setLastTrasactionDate(LocalDateTime.now());

        double commission = 0;
        currentAccountBusiness.setNumberMovements(currentAccountBusiness.getNumberMovements() + 1);
        if(currentAccountBusiness.getPasiveProduct().getNumLimitMovements() < currentAccountBusiness.getNumberMovements()){
            commission = currentAccountBusiness.getPasiveProduct().getTransactionCommission();
        }

        if(transactionCurrentAccountBusinessRequest.getTransactionTypeCurrentAccount().equals(TransactionTypePasPro.RETIRO.name())){
            if(currentAccountBusiness.getAmount() >= transactionCurrentAccountBusinessRequest.getAmount() + commission){
                currentAccountBusiness.setAmount(currentAccountBusiness.getAmount() - transactionCurrentAccountBusinessRequest.getAmount() - commission);
                return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusiness)
                        .switchIfEmpty(Mono.error(new Exception()))
                        .flatMap(CurrentAccountBusinessUpdate -> this.registerTransaction(CurrentAccountBusinessUpdate, transactionCurrentAccountBusiness));
            }
            else{
                return Mono.error(new ModelNotFoundException("Insufficient balance"));
            }
        }
        else if(transactionCurrentAccountBusinessRequest.getTransactionTypeCurrentAccount().equals(TransactionTypePasPro.DEPOSITO.name())){
            currentAccountBusiness.setAmount(currentAccountBusiness.getAmount() + transactionCurrentAccountBusinessRequest.getAmount() - commission);
            return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusiness)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(CurrentAccountBusinessUpdate -> this.registerTransaction(CurrentAccountBusinessUpdate, transactionCurrentAccountBusiness));
        }

        return Mono.error(new ModelNotFoundException("Invalid option"));
    }

    private Mono<TransactionCurrentAccountBusiness> registerTransaction(CurrentAccountBusiness CurrentAccountBusiness, TransactionCurrentAccountBusiness transactionCurrentAccountBusiness){
        transactionCurrentAccountBusiness.setCurrentAccountBusiness(CurrentAccountBusiness);
        return transactionCurrentAccountBusinessRepository.save(transactionCurrentAccountBusiness);
    }

    private Mono<TransactionCurrentAccountBusiness> updateTransaction(TransactionCurrentAccountBusiness transactionCurrentAccountBusiness){
        transactionCurrentAccountBusiness.setUpdatedAt(LocalDateTime.now());
        return transactionCurrentAccountBusinessRepository.save(transactionCurrentAccountBusiness);
    }
}
