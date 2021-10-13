package com.foodtraffic.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class ErrorUtil {

    public static ResponseStatusException responseException(int status, String message) {
        log.error(message);
        if(status == 0) {
            status = 500;
            message = "Service may be down. Please try again";
        } else {
            message = extractErrorMessage(message);
        }
        return new ResponseStatusException(HttpStatus.valueOf(status), message);
    }

    private static String extractErrorMessage(String err) {
        String[] splitString = err.replaceAll("[{}\"]", "").split(",");

        for(String str : splitString) {
            if(str.startsWith("message:")) return str.substring("message:".length());
        }

        return "";
    }
}
