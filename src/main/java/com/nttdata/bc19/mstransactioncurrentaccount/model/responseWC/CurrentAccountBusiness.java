package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class CurrentAccountBusiness extends BaseModel{
    private double amount;
    private String accountNumber;
    private LocalDateTime LastTrasactionDate;
    private String idBusinessClient;
    private String idPasiveProduct;
    private BusinessClient businessClient;
    private PasiveProduct pasiveProduct;
    private int numberMovements;
}
