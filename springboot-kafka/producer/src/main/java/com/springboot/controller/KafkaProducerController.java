package com.springboot.controller;

import com.springboot.model.AlarmDTO;
import com.springboot.model.AlarmJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.service.KafkaSender;

@RestController
@RequestMapping("/kafkaProducer")
public class KafkaProducerController {

	@Autowired
	private KafkaSender sender;

	/**
	 * Send alarm to Kafka
	 */
	@PostMapping
	public ResponseEntity<String> sendAlarm(@RequestBody AlarmDTO alarmDTO){
		sender.sendAlarm(alarmDTO);
		return new ResponseEntity<>("AlarmJSON sent to Kafka", HttpStatus.OK);
	}

}
