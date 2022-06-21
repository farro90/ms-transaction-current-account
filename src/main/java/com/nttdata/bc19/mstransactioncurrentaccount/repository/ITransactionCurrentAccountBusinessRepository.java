package com.nttdata.bc19.mstransactioncurrentaccount.repository;

import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountBusiness;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionCurrentAccountBusinessRepository extends ReactiveMongoRepository<TransactionCurrentAccountBusiness, String> {

    Flux<TransactionCurrentAccountBusiness> findByIdCurrentAccountBusiness(String idCurrentAccountBusiness);
}
