package com.aiocdawacs.cloud.stream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiocdawacs.cloud.stream.config.SpringCloudStreamGcpPubSubConfig.PubsubOutboundGateway;
import com.aiocdawacs.cloud.stream.model.CloudEvent;

@Service
public class CloudEventPublisherServiceImpl implements CloudEventPublisherService{

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	@Override
	public void publishMessage(CloudEvent event, AwacsCloudEventProviderEnum provider) {
		messagingGateway.sendToPubsub(event);
	}
}