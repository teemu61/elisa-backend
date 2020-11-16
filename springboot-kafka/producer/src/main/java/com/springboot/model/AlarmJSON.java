package com.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AlarmJSON {

    @JsonProperty("metadata")
    MetadataJSON metadataJSON;
}