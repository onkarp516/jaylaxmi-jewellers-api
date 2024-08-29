package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private String message = null;
    private int responseStatus;
    private Object response;
    private String data;

}
