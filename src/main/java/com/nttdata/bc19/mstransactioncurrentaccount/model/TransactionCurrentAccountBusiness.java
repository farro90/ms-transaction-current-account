package com.nttdata.bc19.mstransactioncurrentaccount.model;

import com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC.CurrentAccountBusiness;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionCurrentAccountBusiness extends BaseModel{
    //private  String code;
    private String idCurrentAccountBusiness;
    private CurrentAccountBusiness currentAccountBusiness;
    private LocalDateTime transactionDate;
    private String transactionTypeCurrentAccount;
    private double amount;
}
