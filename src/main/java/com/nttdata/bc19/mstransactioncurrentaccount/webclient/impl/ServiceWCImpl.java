package com.nttdata.bc19.mstransactioncurrentaccount.webclient.impl;

import com.nttdata.bc19.mstransactioncurrentaccount.webclient.IServiceWC;
import com.nttdata.bc19.mstransactioncurrentaccount.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ServiceWCImpl implements IServiceWC {

    //private static final String URL_GATEWAY = "${my.property.URL_GATEWAY}";

    private static final String URL_GATEWAY = "http://localhost:8080";

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<CurrentAccountPerson> findCurrentAccountPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/current-account/person/".concat(id))
                .retrieve()
                .bodyToMono(CurrentAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<SavingAccountPerson> findSavingAccountPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/saving-account/".concat(id))
                .retrieve()
                .bodyToMono(SavingAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("SAVING ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<FixedTermAccountPerson> findFixedTermAccountPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/fixed-term-account/".concat(id))
                .retrieve()
                .bodyToMono(FixedTermAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("FIXED TERM ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountBusiness> findCurrentAccountBusinessById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/current-account/business/".concat(id))
                .retrieve()
                .bodyToMono(CurrentAccountBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditPerson> findCreditPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/credit/person/".concat(id))
                .retrieve()
                .bodyToMono(CreditPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditCardPerson> findCreditCardPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/credit-card/person/".concat(id))
                .retrieve()
                .bodyToMono(CreditCardPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT CARD PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditBusiness> findCreditBusinessById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/credit/business/".concat(id))
                .retrieve()
                .bodyToMono(CreditBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditCardBusiness> findCreditCardBusinessById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/credit-card/business/".concat(id))
                .retrieve()
                .bodyToMono(CreditCardBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT CARD BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountPerson> updateCurrentAccountPerson(CurrentAccountPerson currentAccountPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/current-account/person/")
                .body(Mono.just(currentAccountPerson), CurrentAccountPerson.class)
                .retrieve()
                .bodyToMono(CurrentAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<FixedTermAccountPerson> updateFixedTermAccountPerson(FixedTermAccountPerson fixedTermAccountPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/fixed-term-account/")
                .body(Mono.just(fixedTermAccountPerson), FixedTermAccountPerson.class)
                .retrieve()
                .bodyToMono(FixedTermAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("FIXED TERM ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<SavingAccountPerson> updateSavingAccountPerson(SavingAccountPerson savingAccountPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/saving-account/")
                .body(Mono.just(savingAccountPerson), SavingAccountPerson.class)
                .retrieve()
                .bodyToMono(SavingAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("SAVING ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountBusiness> updateCurrentAccountBusiness(CurrentAccountBusiness currentAccountBusiness) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/current-account/business/")
                .body(Mono.just(currentAccountBusiness), CurrentAccountBusiness.class)
                .retrieve()
                .bodyToMono(CurrentAccountBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditPerson> updateCreditPerson(CreditPerson creditPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/credit/person/")
                .body(Mono.just(creditPerson), CreditPerson.class)
                .retrieve()
                .bodyToMono(CreditPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditBusiness> updateCreditBusiness(CreditBusiness creditBusiness) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/credit/business/")
                .body(Mono.just(creditBusiness), CreditBusiness.class)
                .retrieve()
                .bodyToMono(CreditBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditCardPerson> updateCreditCardPerson(CreditCardPerson creditCardPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/credit-card/person/")
                .body(Mono.just(creditCardPerson), CreditCardPerson.class)
                .retrieve()
                .bodyToMono(CreditCardPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT CARD PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CreditCardBusiness> updateCreditCardBusiness(CreditCardBusiness creditCardBusiness) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/credit-card/business/")
                .body(Mono.just(creditCardBusiness), CreditCardBusiness.class)
                .retrieve()
                .bodyToMono(CreditCardBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CREDIT CARD BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<PersonClient> findPersonClientById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/client/person/".concat(id))
                .retrieve()
                .bodyToMono(PersonClient.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CLIENT NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<BusinessClient> findBusinessClientById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/client/business/".concat(id))
                .retrieve()
                .bodyToMono(BusinessClient.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<ActiveProduct> findActiveProductById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/product/active/".concat(id))
                .retrieve()
                .bodyToMono(ActiveProduct.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("PRODUCT NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<PasiveProduct> findPasiveProductById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/product/pasive/".concat(id))
                .retrieve()
                .bodyToMono(PasiveProduct.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("PRODUCT NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }
}
