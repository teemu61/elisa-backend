package com.springboot.service;

import com.springboot.mapper.AlarmMapper;
import com.springboot.model.Alarm;
import com.springboot.model.AlarmDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReciever {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReciever.class);

	private  AlarmService alarmService;

	private AlarmMapper alarmMapper;

	public KafkaReciever(AlarmService alarmService, AlarmMapper alarmMapper) {
		this.alarmService = alarmService;
		this.alarmMapper = alarmMapper;
	}


	/**
	 * Listener for Kafka
	 */
	@KafkaListener(topics = "${kafka.topic.name}", group = "${kafka.consumer.group.id}")
	public Alarm recieveAlarm(AlarmDTO alarmDTO) {
		LOGGER.info("AlarmDTO - " + alarmDTO.toString() + " recieved");
		return alarmService.save(alarmMapper.dtoToAlarm(alarmDTO));
	}


}
