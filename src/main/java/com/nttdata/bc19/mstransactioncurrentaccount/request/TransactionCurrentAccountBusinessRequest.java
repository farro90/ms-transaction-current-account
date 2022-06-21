package com.nttdata.bc19.mstransactioncurrentaccount.request;

import lombok.Data;

@Data
public class TransactionCurrentAccountBusinessRequest {
    private String idCurrentAccountBusiness;
    private double amount;
    private String transactionTypeCurrentAccount;
}
