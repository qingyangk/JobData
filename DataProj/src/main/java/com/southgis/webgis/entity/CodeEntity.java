package com.southgis.webgis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CodeEntity {
    @JsonProperty("code")
    int code;
}
