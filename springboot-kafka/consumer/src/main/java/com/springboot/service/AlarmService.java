package com.springboot.service;

import com.springboot.model.*;
import com.springboot.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Service to read Alarms stored in MySQL database
 */
@Service
public class AlarmService {

    AlarmRepository alarmRepository;

    @Autowired
    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    public Alarm save(Alarm alarm) {

        return alarmRepository.save(alarm);
    }

    public List<Alarm> getERA015Alarms(String id) {
        return alarmRepository.findAlarmByVnocAlarmID(id);
    }

    public List<Alarm> getAll() {
        return alarmRepository.findAll();
    }

    /**
     * Count ERA015 alarms per hour and sort them in decending order
     */
    public List<AlarmHourDTO> getERA015AlarmsPerHour() {

        List<Alarm> alarms = alarmRepository.findAlarmByVnocAlarmID("ERA015");

        List<AlarmHourDTO> list = new ArrayList<>();

        Map<Integer, Map<Integer, Map<Integer, Map<Integer, List<Alarm>>>>> map = alarms.stream()
                .collect(groupingBy(i -> i.getAlarmEventTime().getYear(),
                        groupingBy(i -> i.getAlarmEventTime().getMonth(),
                                groupingBy(i -> i.getAlarmEventTime().getDate(),
                                        groupingBy(i -> i.getAlarmEventTime().getHours())))));

        for (Map.Entry<Integer, Map<Integer, Map<Integer, Map<Integer, List<Alarm>>>>> entryYear : map.entrySet()) {
            for (Map.Entry<Integer, Map<Integer, Map<Integer, List<Alarm>>>> entryMonth : entryYear.getValue().entrySet()) {
                for (Map.Entry<Integer, Map<Integer, List<Alarm>>> entryDate : entryMonth.getValue().entrySet()) {
                    for (Map.Entry<Integer, List<Alarm>> entryHour : entryDate.getValue().entrySet()) {
                        Date d = getDate(entryYear, entryMonth, entryDate, entryHour);
                        int count = entryHour.getValue().size();
                        AlarmHourDTO perHour = AlarmHourDTO.builder().date(d).count(count).build();
                        list.add(perHour);
                    }
                }
            }
        }

        Comparator<AlarmHourDTO> nameComparator = (s1, s2) -> s1.getDate().compareTo(s2.getDate());
        Collections.sort(list, nameComparator);

        return list;
    }

    private Date getDate(Map.Entry<Integer, Map<Integer, Map<Integer, Map<Integer, List<Alarm>>>>> entryYear, Map.Entry<Integer, Map<Integer, Map<Integer, List<Alarm>>>> entryMonth, Map.Entry<Integer, Map<Integer, List<Alarm>>> entryDate, Map.Entry<Integer, List<Alarm>> entryHour) {
        Date d = new Date();
        d.setYear(entryYear.getKey());
        d.setMonth(entryMonth.getKey());
        d.setDate(entryDate.getKey());
        d.setHours(entryHour.getKey());
        d.setMinutes(0);
        d.setSeconds(0);
        return d;
    }

    /**
     * Count most frequent alarm IDs and sort them in decending order. Limit result to 5 most common alarm.
     */
    public List<AlarmFreqDTO> getMostFrequentAlarms() {

        List<Alarm> alarms = alarmRepository.findAll();

        Map<String, Long> collect = alarms.stream()
                .collect(groupingBy(Alarm::getVnocAlarmID, Collectors.counting()));

        List<AlarmFreqDTO> result = collect.entrySet().stream()
                .map(i -> AlarmFreqDTO.builder().node(i.getKey()).count(i.getValue()).build())
                .collect(Collectors.toList());

        Comparator<AlarmFreqDTO> nameComparator = (s1, s2) -> s1.getCount().compareTo(s2.getCount());
        Collections.sort(result, nameComparator.reversed());

        if (result.size() > 5) {
            return result.subList(0, 5);
        }

        return result;

    }

    /**
     * Find nodes with most alarms. Sort results in decenting order and limit results to 5 nodes.
     */
    public List<AlarmNodeDTO> getNodesWithMostAlarms() {

        List<Alarm> alarms = alarmRepository.findAll();
        List<AlarmNodeDTO> alarmNodeDTOS = new ArrayList<>();

        Map<String, Long> collect = alarms.stream()
                .collect(groupingBy(Alarm::getAffectedNode, Collectors.counting()));

        List<AlarmNodeDTO> result = collect.entrySet().stream()
                .map(i -> AlarmNodeDTO.builder().node(i.getKey()).count(i.getValue()).build())
                .collect(Collectors.toList());


        Comparator<AlarmNodeDTO> nameComparator = (s1, s2) -> s1.getCount().compareTo(s2.getCount());
        Collections.sort(result, nameComparator.reversed());

        if (result.size() > 5) {
            return result.subList(0, 5);
        }

        return result.subList(0, 5);

    }

}


