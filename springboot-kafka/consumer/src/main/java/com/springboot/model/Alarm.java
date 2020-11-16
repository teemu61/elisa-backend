package com.springboot.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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
    Date alarmEventTime;
    String vnocAlarmID;

}
