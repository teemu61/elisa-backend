package com.springboot.repository;

import com.springboot.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAlarmByVnocAlarmID(String vnocAlarmID);

}
