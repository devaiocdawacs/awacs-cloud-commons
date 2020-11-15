![Maven Build](https://github.com/girishaiocdawacs/smart-pharmacy-event-management/workflows/Maven%20Build/badge.svg)

![Maven Deploy To Github Package](https://github.com/girishaiocdawacs/smart-pharmacy-event-management/workflows/Maven%20Deploy%20To%20Github%20Package/badge.svg)

###### Apache-Kafka Binary Distribution [Download](http://apachemirror.wuchna.com/kafka/2.3.1/kafka_2.11-2.3.1.tgz).

###### Strat Zookeeper server
> zookeeper-server-start.bat D:\softwares\kafka_2.11-2.3.1\config\zookeeper.properties

###### Strat Kafka server 
> kafka-server-start.bat D:\softwares\kafka_2.11-2.3.1\config\server.properties

###### Create Topic
> kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic awacs_global

###### List down all available topics
> kafka-topics.bat --list --zookeeper localhost:2181

###### Produce a message
> kafka-console-producer.bat --broker-list localhost:9092 --topic awacs_global

###### Consume a message
> kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic awacs_global
