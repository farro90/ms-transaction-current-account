package com.nttdata.bc19.mstransactioncurrentaccount.service.impl;

import com.nttdata.bc19.mstransactioncurrentaccount.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountPerson;
import com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC.CurrentAccountPerson;
import com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC.PasiveProduct;
import com.nttdata.bc19.mstransactioncurrentaccount.repository.ITransactionCurrentAccountPersonRepository;
import com.nttdata.bc19.mstransactioncurrentaccount.request.TransactionCurrentAccountPersonRequest;
import com.nttdata.bc19.mstransactioncurrentaccount.service.ITransactionCurrentAccountPersonService;
import com.nttdata.bc19.mstransactioncurrentaccount.util.TransactionTypePasPro;
import com.nttdata.bc19.mstransactioncurrentaccount.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCurrentAccountPersonServiceImpl implements ITransactionCurrentAccountPersonService {

    @Autowired
    ITransactionCurrentAccountPersonRepository transactionCurrentAccountPersonRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionCurrentAccountPerson> create(TransactionCurrentAccountPersonRequest transactionCurrentAccountPersonRequest) {
        return clientServiceWC.findCurrentAccountPersonById(transactionCurrentAccountPersonRequest.getIdCurrentAccountPerson())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(currentAccountResponse -> clientServiceWC.findPasiveProductById(currentAccountResponse.getIdPasiveProduct())
                        .switchIfEmpty(Mono.error(new Exception()))
                        .flatMap(pasiveProductResponse -> businessLogicTransaction(transactionCurrentAccountPersonRequest, currentAccountResponse, pasiveProductResponse))
                );
    }

    @Override
    public Mono<TransactionCurrentAccountPerson> update(TransactionCurrentAccountPerson transactionCurrentAccountPerson) {
        transactionCurrentAccountPerson.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findCurrentAccountPersonById(transactionCurrentAccountPerson.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CurrentAccountPerson -> this.updateTransaction(transactionCurrentAccountPerson));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionCurrentAccountPersonRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionCurrentAccountPerson> findById(String id) {
        return transactionCurrentAccountPersonRepository.findById(id);
    }

    @Override
    public Flux<TransactionCurrentAccountPerson> findAll() {
        return transactionCurrentAccountPersonRepository.findAll();
    }

    @Override
    public Flux<TransactionCurrentAccountPerson> findByIdCurrentAccountPerson(String idCurrentAccountPerson) {
        return transactionCurrentAccountPersonRepository.findByIdCurrentAccountPerson(idCurrentAccountPerson);
    }

    private Mono<TransactionCurrentAccountPerson> businessLogicTransaction(TransactionCurrentAccountPersonRequest transactionCurrentAccountPersonRequest, CurrentAccountPerson currentAccountPerson, PasiveProduct pasiveProductCurrentAccount){

        TransactionCurrentAccountPerson transactionCurrentAccountPerson = new TransactionCurrentAccountPerson();
        transactionCurrentAccountPerson.setId(new ObjectId().toString());
        transactionCurrentAccountPerson.setIdCurrentAccountPerson(transactionCurrentAccountPersonRequest.getIdCurrentAccountPerson());
        transactionCurrentAccountPerson.setTransactionTypeCurrentAccount(transactionCurrentAccountPersonRequest.getTransactionTypeCurrentAccount());
        transactionCurrentAccountPerson.setTransactionDate(LocalDateTime.now());
        transactionCurrentAccountPerson.setCreatedAt(LocalDateTime.now());

        if(currentAccountPerson.getLastTrasactionDate().getMonth().getValue() < LocalDateTime.now().getMonth().getValue()){
            currentAccountPerson.setNumberMovements(0);
        }
        currentAccountPerson.setLastTrasactionDate(LocalDateTime.now());

        double commission = 0;
        currentAccountPerson.setNumberMovements(currentAccountPerson.getNumberMovements() + 1);
        if(pasiveProductCurrentAccount.getNumLimitMovements() < currentAccountPerson.getNumberMovements()){
            commission = pasiveProductCurrentAccount.getTransactionCommission();
        }

        if(transactionCurrentAccountPersonRequest.getTransactionTypeCurrentAccount().equals(TransactionTypePasPro.RETIRO.name())){
            if(currentAccountPerson.getAmount() >= transactionCurrentAccountPersonRequest.getAmount() + commission){
                currentAccountPerson.setAmount(currentAccountPerson.getAmount() - transactionCurrentAccountPersonRequest.getAmount() - commission);
                return clientServiceWC.updateCurrentAccountPerson(currentAccountPerson)
                        .switchIfEmpty(Mono.error(new Exception()))
                        .flatMap(CurrentAccountPersonUpdate -> this.registerTransaction(CurrentAccountPersonUpdate, transactionCurrentAccountPerson));
            }
            else{
                return Mono.error(new ModelNotFoundException("Insufficient balance."));
            }
        }
        else if(transactionCurrentAccountPersonRequest.getTransactionTypeCurrentAccount().equals(TransactionTypePasPro.DEPOSITO.name())){
            currentAccountPerson.setAmount(currentAccountPerson.getAmount() + transactionCurrentAccountPersonRequest.getAmount() - commission);
            return clientServiceWC.updateCurrentAccountPerson(currentAccountPerson)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(CurrentAccountPersonUpdate -> this.registerTransaction(CurrentAccountPersonUpdate, transactionCurrentAccountPerson));
        }

        return Mono.error(new ModelNotFoundException("Invalid option."));
    }

    private Mono<TransactionCurrentAccountPerson> registerTransaction(CurrentAccountPerson CurrentAccountPerson, TransactionCurrentAccountPerson transactionCurrentAccountPerson){
        transactionCurrentAccountPerson.setCurrentAccountPerson(CurrentAccountPerson);
        return transactionCurrentAccountPersonRepository.save(transactionCurrentAccountPerson);
    }

    private Mono<TransactionCurrentAccountPerson> updateTransaction(TransactionCurrentAccountPerson transactionCurrentAccountPerson){
        transactionCurrentAccountPerson.setUpdatedAt(LocalDateTime.now());
        return transactionCurrentAccountPersonRepository.save(transactionCurrentAccountPerson);
    }
}
