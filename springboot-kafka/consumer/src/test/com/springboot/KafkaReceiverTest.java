package com.springboot;


import com.springboot.model.AlarmFreqDTO;
import com.springboot.model.AlarmHourDTO;
import com.springboot.model.AlarmNodeDTO;
import com.springboot.service.AlarmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.List;


/**
 * Manual integration tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaReceiverTest {

    @Autowired
    AlarmService alarmService;

    @Test
    public void testERA015AlarmsPerHour() {
        List<AlarmHourDTO> alarms = alarmService.getERA015AlarmsPerHour();
        assertNotNull(alarms);
        System.out.println(alarms.size());
    }

    @Test
    public void testMostFreqAlarms() {
        List<AlarmFreqDTO> alarms = alarmService.getMostFrequentAlarms();
        assertNotNull(alarms);
        System.out.println(alarms.size());
    }

    @Test
    public void testNodesWithMostAlarms() {
        List<AlarmNodeDTO> alarms = alarmService.getNodesWithMostAlarms();
        assertNotNull(alarms);
        System.out.println(alarms.size());
    }
}
