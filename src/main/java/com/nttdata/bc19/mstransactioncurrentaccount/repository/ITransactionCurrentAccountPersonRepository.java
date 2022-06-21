package com.nttdata.bc19.mstransactioncurrentaccount.repository;

import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionCurrentAccountPersonRepository extends ReactiveMongoRepository<TransactionCurrentAccountPerson, String> {

    Flux<TransactionCurrentAccountPerson> findByIdCurrentAccountPerson(String idCurrentAccountPerson);
}
