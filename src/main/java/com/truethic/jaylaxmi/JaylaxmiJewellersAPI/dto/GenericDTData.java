package com.truethic.jaylaxmi.JaylaxmiJewellersAPI.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenericDTData<T> {
    private List<T> rows;
    private Integer totalRows;//total rows
}
