package com.ismail.coronaviroustracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationStats {
    private String state;
    private String country;
    private long  lastDayTotal;
    private long newCase;
}
