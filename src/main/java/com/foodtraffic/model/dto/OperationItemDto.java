package com.foodtraffic.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class OperationItemDto {

    private long id;

    private long operationId;

    private String dayOfWeek;

    private boolean isClosed;

    private String reason;

    private LocalDate date;

    private String openTime;

    private String closeTime;
}
