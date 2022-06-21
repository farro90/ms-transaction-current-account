package com.nttdata.bc19.mstransactioncurrentaccount.service;

import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountPerson;
import com.nttdata.bc19.mstransactioncurrentaccount.request.TransactionCurrentAccountPersonRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionCurrentAccountPersonService {

    Mono<TransactionCurrentAccountPerson> create(TransactionCurrentAccountPersonRequest transactionCurrentAccountPersonRequest);
    Mono<TransactionCurrentAccountPerson> update(TransactionCurrentAccountPerson transactionCurrentAccountPerson);
    Mono<Void>deleteById(String id);
    Mono<TransactionCurrentAccountPerson> findById(String id);
    Flux<TransactionCurrentAccountPerson> findAll();

    Flux<TransactionCurrentAccountPerson> findByIdCurrentAccountPerson(String idCurrentAccountPerson);
}
