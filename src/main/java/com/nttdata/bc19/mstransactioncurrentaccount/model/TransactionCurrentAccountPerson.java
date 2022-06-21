package com.nttdata.bc19.mstransactioncurrentaccount.model;

import com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC.CurrentAccountPerson;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionCurrentAccountPerson extends BaseModel{
    //private  String code;
    private String idCurrentAccountPerson;
    private CurrentAccountPerson currentAccountPerson;
    private LocalDateTime transactionDate;
    private String transactionTypeCurrentAccount;
    private double amount;
}
