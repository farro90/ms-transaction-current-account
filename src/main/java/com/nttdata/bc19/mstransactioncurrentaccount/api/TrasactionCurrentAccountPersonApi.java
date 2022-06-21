package com.nttdata.bc19.mstransactioncurrentaccount.api;

import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountPerson;
import com.nttdata.bc19.mstransactioncurrentaccount.request.TransactionCurrentAccountPersonRequest;
import com.nttdata.bc19.mstransactioncurrentaccount.service.ITransactionCurrentAccountPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/current/person")
public class TrasactionCurrentAccountPersonApi {

    @Autowired
    private ITransactionCurrentAccountPersonService transactionCurrentAccountPersonService;

    @PostMapping
    public Mono<TransactionCurrentAccountPerson> create(@RequestBody TransactionCurrentAccountPersonRequest transactionCurrentAccountPersonRequest){
        return transactionCurrentAccountPersonService.create(transactionCurrentAccountPersonRequest);
    }

    @PutMapping
    public Mono<TransactionCurrentAccountPerson> update(@RequestBody TransactionCurrentAccountPerson transactionCurrentAccountPerson){
        return transactionCurrentAccountPersonService.update(transactionCurrentAccountPerson);
    }

    @GetMapping
    public Flux<TransactionCurrentAccountPerson> findAll(){
        return transactionCurrentAccountPersonService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionCurrentAccountPerson> findById(@PathVariable String id){ return transactionCurrentAccountPersonService.findById(id); }

    @GetMapping("/find/{idCurrentAcountPerson}")
    public Flux<TransactionCurrentAccountPerson> findByIdPasProPerCli(@PathVariable String idCurrentAcountPerson){
        return transactionCurrentAccountPersonService.findByIdCurrentAccountPerson(idCurrentAcountPerson);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionCurrentAccountPersonService.deleteById(id);
    }
}
