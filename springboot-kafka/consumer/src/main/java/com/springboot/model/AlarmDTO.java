package com.springboot.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonDeserialize(as = AlarmDTO.class)
public class AlarmDTO {

    String affectedNode;
    String affectedEquipment;
    String affectedSite;
    String alarmCategory;
    String alarmGroup;
    String alarmCSN;
    String alarmID;
    String alarmMO;
    String alarmLastSeqNo;
    String alarmNotificationType;
    String alarmEventTime;
    String vnocAlarmID;
}
