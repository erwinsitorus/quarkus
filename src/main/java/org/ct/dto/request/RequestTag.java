package org.ct.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestTag {

    @NotEmpty
    private String label;
}
