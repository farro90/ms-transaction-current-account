package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;

@Data
public class CreditBusiness extends BaseModel{
    private double amountGiven;
    private double amountPaid;
    private double interestRate;
    private String idBusinessClient;
    private String idActiveProduct;
    private BusinessClient businessClient;
    private ActiveProduct activeProduct;
}
