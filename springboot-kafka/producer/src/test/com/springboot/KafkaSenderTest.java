package com.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mapping.AlarmMapper;
import com.springboot.model.AlarmDTO;
import com.springboot.model.AlarmJSON;
import com.springboot.model.MetadataJSON;
import com.springboot.service.KafkaSender;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Manual integration tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSenderTest {

    @Autowired
    KafkaSender kafkaSender;

    @Autowired
    AlarmMapper alarmMapper;


    @Test
    public void sendAlarmsToKafka() throws IOException {

        System.out.println("test run");
        String json = getJson();
        ObjectMapper mapper = new ObjectMapper();

        AlarmJSON[] alarmJSONS = mapper.readValue(json, AlarmJSON[].class);
        List<AlarmJSON> alarmJSONList = Arrays.asList(alarmJSONS);

        alarmJSONList.forEach(i -> {
            AlarmDTO alarmDTO = alarmMapper.jsonToDto(i.getMetadataJSON());
            kafkaSender.sendAlarm(alarmDTO);
            });
    }

    public String getJson() throws  IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File file = new File(classLoader.getResource("packages_all.json").getFile());
        System.out.println("File Found : " + file.exists());
        return new String(Files.readAllBytes(file.toPath()));
    }
}