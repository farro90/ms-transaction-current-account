package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;

@Data
public class PasiveProduct {
    private String id;
    private String name;
    private double transactionCommission;
    private double minimumOpeningAmount;
    private int numLimitMovements;
    private Boolean allowBusinessClient;
    private Boolean allowPersonClient;
}
