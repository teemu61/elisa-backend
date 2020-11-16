package com.springboot.service;

import com.springboot.model.AlarmDTO;
import com.springboot.model.AlarmJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Value("${kafka.topic.name}")
	private String topicName;


	/**
	 * Send alarm to Kafka
	 */
	public void sendAlarm(AlarmDTO alarmDTO) {

		Map<String, Object> headers = new HashMap<>();
		headers.put(KafkaHeaders.TOPIC, topicName);
		if (alarmDTO.getAffectedNode() != null && alarmDTO.getAlarmEventTime() != null) {
			kafkaTemplate.send(new GenericMessage<AlarmDTO>(alarmDTO, headers));
			LOGGER.info("AlarmDTO - " + alarmDTO.toString() + " sent to Kafka Topic - " + topicName);
		}
	}
}
