package com.nttdata.bc19.mstransactioncurrentaccount.service;

import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountBusiness;
import com.nttdata.bc19.mstransactioncurrentaccount.request.TransactionCurrentAccountBusinessRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionCurrentAccountBusinessService {

    Mono<TransactionCurrentAccountBusiness> create(TransactionCurrentAccountBusinessRequest transactionCurrentAccountBusinessRequest);
    Mono<TransactionCurrentAccountBusiness> update(TransactionCurrentAccountBusiness transactionCurrentAccountBusiness);
    Mono<Void>deleteById(String id);
    Mono<TransactionCurrentAccountBusiness> findById(String id);
    Flux<TransactionCurrentAccountBusiness> findAll();

    Flux<TransactionCurrentAccountBusiness> findByIdCurrentAccountBusiness(String idCurrentAccountBusiness);
}
