package com.springboot.mapper;

import com.springboot.model.Alarm;
import com.springboot.model.AlarmDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AlarmMapper {

    @Mapping(target = "alarmEventTime", source = "alarmEventTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX")
    Alarm dtoToAlarm(AlarmDTO alarmDTO);

}
