package com.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmHourDTO {

    Date date;
    Integer count;

}
