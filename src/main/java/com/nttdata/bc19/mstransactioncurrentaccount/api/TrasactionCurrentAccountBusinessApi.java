package com.nttdata.bc19.mstransactioncurrentaccount.api;

import com.nttdata.bc19.mstransactioncurrentaccount.model.TransactionCurrentAccountBusiness;
import com.nttdata.bc19.mstransactioncurrentaccount.request.TransactionCurrentAccountBusinessRequest;
import com.nttdata.bc19.mstransactioncurrentaccount.service.ITransactionCurrentAccountBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/current/business")
public class TrasactionCurrentAccountBusinessApi {

    @Autowired
    private ITransactionCurrentAccountBusinessService transactionCurrentAccountBusinessService;

    @PostMapping
    public Mono<TransactionCurrentAccountBusiness> create(@RequestBody TransactionCurrentAccountBusinessRequest transactionCurrentAccountBusinessRequest){
        return transactionCurrentAccountBusinessService.create(transactionCurrentAccountBusinessRequest);
    }

    @PutMapping
    public Mono<TransactionCurrentAccountBusiness> update(@RequestBody TransactionCurrentAccountBusiness transactionCurrentAccountBusiness){
        return transactionCurrentAccountBusinessService.update(transactionCurrentAccountBusiness);
    }

    @GetMapping
    public Flux<TransactionCurrentAccountBusiness> findAll(){
        return transactionCurrentAccountBusinessService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionCurrentAccountBusiness> findById(@PathVariable String id){ return transactionCurrentAccountBusinessService.findById(id); }

    @GetMapping("/find/{idCurrentAcountBusiness}")
    public Flux<TransactionCurrentAccountBusiness> findByIdPasProPerCli(@PathVariable String idCurrentAcountBusiness){
        return transactionCurrentAccountBusinessService.findByIdCurrentAccountBusiness(idCurrentAcountBusiness);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionCurrentAccountBusinessService.deleteById(id);
    }
}
