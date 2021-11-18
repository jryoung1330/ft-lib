package com.foodtraffic.model.response;

import lombok.Data;

@Data
public class ResponseMetaData {
    private String prev;
    private String next;
    private Integer page;
    private Integer size;
    private Integer total;
}
