package com.springboot.controller;

import com.springboot.model.AlarmFreqDTO;
import com.springboot.model.AlarmHourDTO;
import com.springboot.model.AlarmNodeDTO;
import com.springboot.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;


    /**
     * Nodes that got the most alarms
     */
    @CrossOrigin
    @RequestMapping(value= "/node")
    public List<AlarmNodeDTO> getNodesWithMostAlarms() {

        return alarmService.getNodesWithMostAlarms();

    }

    /**
     * Most frequent alarms
     */
    @CrossOrigin
    @RequestMapping(value= "/freq")
    public List<AlarmFreqDTO> getMostFrequentAlarms() {

        return alarmService.getMostFrequentAlarms();

    }

    /**
     * Timeline about the ERA015 alarms per hour
     */
    @CrossOrigin
    @RequestMapping(value= "/hour")
    public List<AlarmHourDTO> getAlarmsPerHour() {

        return alarmService.getERA015AlarmsPerHour();

    }

}
