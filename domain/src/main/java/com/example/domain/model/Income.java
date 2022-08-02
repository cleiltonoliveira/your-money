package com.example.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Income {
    private String id;
    private String description;
    private String amount;
    private LocalDateTime date;
}
