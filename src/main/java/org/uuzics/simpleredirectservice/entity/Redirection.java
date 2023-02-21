package org.uuzics.simpleredirectservice.entity;

import lombok.Data;

@Data
public class Redirection {
    private Integer id;
    private String resourceId;
    private String redirectUrl;
    private String redirectType;
}
