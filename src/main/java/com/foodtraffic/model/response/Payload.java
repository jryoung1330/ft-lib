package com.foodtraffic.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class Payload<T> {

    private T data;

    @JsonProperty("_meta")
    private ResponseMetaData metaData;

    public Payload(T data, Page<?> page, String uri) {
        this.data = data;
        this.metaData = createMetaData(page, uri);
    }

    private ResponseMetaData createMetaData(Page<?> page, String uri) {
        ResponseMetaData metaData = new ResponseMetaData();

        if(page.hasPrevious()) {
            metaData.setPrev(uri + "page=" + (page.getNumber()-1) + "&size=" + page.getSize());
        }

        if(page.hasNext()) {
            metaData.setNext(uri + "page=" + (page.getNumber()+1) + "&size=" + page.getSize());
        }

        metaData.setPage(page.getNumber());
        metaData.setSize(page.getSize());
        metaData.setTotal(page.getTotalPages());
        return metaData;
    }
}
