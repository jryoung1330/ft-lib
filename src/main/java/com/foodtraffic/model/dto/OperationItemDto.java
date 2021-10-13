package com.foodtraffic.model.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class OperationItemDto {

    private long id;

    private long operationId;

    private String dayOfWeek;

    private boolean isClosed;

    private String openTime;

    private String closeTime;

    private boolean isEvent;

    private String eventName;

    private String eventUrl;

    private LocalDate eventStartDate;

    private LocalDate eventEndDate;
}
