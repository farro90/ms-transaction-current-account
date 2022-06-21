package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreditPerson extends BaseModel{
    private double amountGiven;
    private double amountPaid;
    private double interestRate;
    private String idPersonClient;
    private String idActiveProduct;
    private PersonClient personClient;
    private ActiveProduct activeProduct;
}
