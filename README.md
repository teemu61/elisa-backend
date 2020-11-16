# Kotlin/Java/Scala developer assignment

In this assignment the applicant should implement a simple application that streams data through Kafka.
The preferred language of the assignment is Kotlin or Java or Scala.

Content of the packages.json:
* affectedNode: the node that sent the alarm
* vnocAlarmID: the alarm Id that represents the alarm type
* alarmEventTime: the time when the event was generated

## Requirements
Create an application that streams the content of package.json through Kafka server, and presents the following:
* Histogram about the most frequent alarms
* Histogram about the nodes that got the most alarms
* Timeline about the ERA015 alarms per hour

Representation can be text based or graphical. No limitation about the technology used.

Kafka can be run easily with docker command:
```
docker-compose -f kafka.yaml up -d
```

The application can use any of the kafka client libraries:
* simple producer-consumer
* kafka streams
* kafka-connect

The code must be built or runnable with one of the following build tools and the instructions for running has to be included in the README.md
* Gradle
* Maven
* SBT
* Make

## Instructions

1. start kafka server

```
docker-compose -f kafka.yaml up -d
```

2. start mysql server

```
docker-compose -f mysql.yaml up -d
```

3. start producer service

```
java -jar springboot-kafka/producer/target/springboot-kafka.producer-0.0.1-SNAPSHOT.jar
```

4. start consumer service

```
java -jar springboot-kafka/consumer/target/springboot-kafka.consumer-0.0.1-SNAPSHOT.jar
```

5. send Alarm data to Kafka server

```
KafkaSenderTest#sendAlarmsToKafka() integration test.
```

6. start separate elisa-front React application

```
npm start
```
 
