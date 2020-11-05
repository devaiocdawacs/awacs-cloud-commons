package com.aiocdawacs.cloud.stream.service;

public enum AwacsCloudEventProviderEnum {

	/**
	 * topic need to create in gcp before using the library
	 * Not used PubSubAdmin
	 */
	GoogleCloudPlatformPubSub("awacs_global"),


	/**
	 * Not implemented 
	 */
	Kafka("awacs_global");

	String topicName;

	private AwacsCloudEventProviderEnum(String topicName) {
		this.topicName = topicName;
	}

}