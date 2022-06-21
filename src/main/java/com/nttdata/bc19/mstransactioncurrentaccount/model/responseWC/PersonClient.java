package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;

@Data
public class PersonClient {
    private String id;
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private String gender;
    private String address;
    private String phone;
    private String email;
}
