package com.aiocdawacs.cloud.stream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aiocdawacs.cloud.stream.config.GooglePubSubConfig.PubsubOutboundGateway;
import com.aiocdawacs.cloud.stream.model.CloudEvent;

@Component
public class CloudEventPublisherServiceImpl implements CloudEventPublisherService{

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	@Override
	public void publishMessage(CloudEvent event, AwacsCloudEventProviderEnum provider) {
		switch(provider) {
		case GoogleCloudPlatformPubSub:
			messagingGateway.sendToPubsub(event);
			break;
		case Kafka:
		default:
			throw new RuntimeException("Not implemented");
		}
	}

	public CloudEventPublisherServiceImpl(PubsubOutboundGateway messagingGateway) {
		super();
		this.messagingGateway = messagingGateway;
	}

	
}