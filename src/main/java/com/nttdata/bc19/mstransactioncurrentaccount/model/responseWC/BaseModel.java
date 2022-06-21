package com.nttdata.bc19.mstransactioncurrentaccount.model.responseWC;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class BaseModel {
    @Id
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
