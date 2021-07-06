package org.ct.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RequestPost {
    private Long postId;

    @NotEmpty
    private String content;

    @NotEmpty
    private String title;

    private Long[] tagId;
}
