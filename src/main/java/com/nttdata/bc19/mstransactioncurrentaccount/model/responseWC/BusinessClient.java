package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;

@Data
public class BusinessClient {
    private String id;
    private String name;
    private String tradeName;
    private String ruc;
    private String address;
    private String phone;
    private String email;
}
